package rcp.tools;

// Original version: https://tips4java.wordpress.com/2009/06/14/moving-windows/

import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;

public class ComponentMover extends MouseAdapter {
	private Insets dragInsets, edgeInsets;
	private Dimension snapSize;

	private boolean changeCursor;

	private Component destination, source;

	private Point pressed, location;

	private Cursor originalCursor;

	private boolean autoscrolls, potentialDrag;

	/**
	 * Constructor for moving individual components. The components must be
	 * regisetered using the registerComponent() method.
	 */
	public ComponentMover() {
		dragInsets = new Insets(0, 0, 0, 0);
		snapSize = new Dimension(1, 1);
		edgeInsets = new Insets(0, 0, 0, 0);
		changeCursor = true;
	}

	/**
	 * Add the required listeners to the specified component
	 *
	 * @param component
	 *            the component the listeners are added to
	 */
	public void registerComponent(Component... components) {
		for (Component component : components)
			component.addMouseListener(this);
	}

	/**
	 * Setup the variables used to control the moving of the component:
	 *
	 * source - the source component of the mouse event destination - the
	 * component that will ultimately be moved pressed - the Point where the
	 * mouse was pressed in the destination component coordinates.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		source = e.getComponent();
		int width = source.getSize().width - dragInsets.left - dragInsets.right;
		int height = source.getSize().height - dragInsets.top - dragInsets.bottom;
		Rectangle r = new Rectangle(dragInsets.left, dragInsets.top, width, height);

		if (r.contains(e.getPoint()))
			setupForDragging(e);
	}

	private void setupForDragging(MouseEvent e) {
		source.addMouseMotionListener(this);
		potentialDrag = true;

		// Determine the component that will ultimately be moved

		destination = source;

		pressed = e.getLocationOnScreen();
		location = destination.getLocation();

		if (changeCursor) {
			originalCursor = source.getCursor();
			source.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		}

		// Making sure autoscrolls is false will allow for smoother dragging of
		// individual components

		if (destination instanceof JComponent) {
			JComponent jc = (JComponent) destination;
			autoscrolls = jc.getAutoscrolls();
			jc.setAutoscrolls(false);
		}
	}

	/**
	 * Move the component to its new location. The dragged Point must be in the
	 * destination coordinates.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		Point dragged = e.getLocationOnScreen();
		int dragX = getDragDistance(dragged.x, pressed.x, snapSize.width);
		int dragY = getDragDistance(dragged.y, pressed.y, snapSize.height);

		int locationX = location.x + dragX;
		int locationY = location.y + dragY;

		// Mouse dragged events are not generated for every pixel the mouse
		// is moved. Adjust the location to make sure we are still on a
		// snap value.

		while (locationX < edgeInsets.left)
			locationX += snapSize.width;

		while (locationY < edgeInsets.top)
			locationY += snapSize.height;

		Dimension d = getBoundingSize(destination);

		while (locationX + destination.getSize().width + edgeInsets.right > d.width)
			locationX -= snapSize.width;

		while (locationY + destination.getSize().height + edgeInsets.bottom > d.height)
			locationY -= snapSize.height;

		// Adjustments are finished, move the component

		destination.setLocation(locationX, locationY);
	}

	/*
	 * Determine how far the mouse has moved from where dragging started (Assume
	 * drag direction is down and right for positive drag distance)
	 */
	private int getDragDistance(int larger, int smaller, int snapSize) {
		int halfway = snapSize / 2;
		int drag = larger - smaller;
		drag += (drag < 0) ? -halfway : halfway;
		drag = (drag / snapSize) * snapSize;

		return drag;
	}

	/*
	 * Get the bounds of the parent of the dragged component.
	 */
	private Dimension getBoundingSize(Component source) {
		if (source instanceof Window) {
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Rectangle bounds = env.getMaximumWindowBounds();
			return new Dimension(bounds.width, bounds.height);
		} else {
			return source.getParent().getSize();
		}
	}

	/**
	 * Restore the original state of the Component
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (!potentialDrag)
			return;

		source.removeMouseMotionListener(this);
		potentialDrag = false;

		if (changeCursor)
			source.setCursor(originalCursor);

		if (destination instanceof JComponent) {
			((JComponent) destination).setAutoscrolls(autoscrolls);
		}
	}
}

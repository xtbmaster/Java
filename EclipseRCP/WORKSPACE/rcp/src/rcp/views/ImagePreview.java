package rcp.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;

import rcp.MyEventConsts;
import rcp.entities.Img;
import rcp.tools.ComponentMover;

import java.awt.Color;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;

/**
 * Class to display images on a panel.
 * @author art
 *
 */
public class ImagePreview {

	JPanel container;
	ComponentMover cm;
	Frame frame;

	@PostConstruct
	public void createComposite(Composite parent) {

		container = new JPanel();
		cm = new ComponentMover();

		Composite swtAwtComponent = new Composite(parent, SWT.CENTER);
		frame = SWT_AWT.new_Frame(swtAwtComponent);

		container.setBackground(Color.WHITE);
		container.setLayout(null);
		frame.add(container);
	}

	/**
	 * Method listens to ADD_ELEMENT event topic, to receive an image and add it to a frame. 
	 * @param img
	 */
	@Inject
	@Optional
	public void addElement(@UIEventTopic(MyEventConsts.ADD_ELEMENT) BufferedImage img) {

		JLabel label = new JLabel(new ImageIcon(img));
		Dimension size = label.getPreferredSize();
		label.setBounds(10, 10, size.width, size.height);

		cm.registerComponent(label);
		container.add(label);
		container.repaint();
	}

	/**
	 * Method listens to HIDE_ELEMENT event topic, to hide/display element on a frame. 
	 * @param img
	 */
	@Inject
	@Optional
	public void changeVisibility(@UIEventTopic(MyEventConsts.HIDE_ELEMENT) int elementIndex) {
		Component target = container.getComponent(elementIndex);
		target.setVisible(!target.isVisible());
	}
}



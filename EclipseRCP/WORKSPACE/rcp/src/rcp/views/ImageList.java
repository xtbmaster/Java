package rcp.views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import rcp.MyEventConsts;
import rcp.views.managers.ImagePreviewManager;

public class ImageList {

	Button addButton;
	Table table;
	ImagePreviewManager ipm;
	
	@Inject
	EPartService partService;

	@Inject
	IEventBroker eventBroker;

	@PostConstruct
	public void createComposite(Composite parent) {
		// parent.setLayout(new GridLayout(1, false));

		addButton = new Button(parent, SWT.NONE);
		addButton.setLayoutData(new GridLayout(1, false));
		addButton.setText("Upload");
		addButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TableItem item;
				BufferedImage imgTarget;
				String fileName;
				
				FileDialog dlg = new FileDialog(addButton.getShell(), SWT.OPEN);
				dlg.setText("Select image");
				
				String path = dlg.open();

				if (path != null) {
					
					ipm = ImagePreviewManager.getInstance();

					imgTarget = ipm.uploadImage(path);

					if (imgTarget != null) {
						
						item = new TableItem(table, SWT.DEFAULT);
						item.setChecked(true);
						
						fileName = ipm.getTableItemName(dlg.getFileName());
						item.setText(fileName);
						
						ipm.addToCollection(imgTarget, fileName);
											
						eventBroker.send(MyEventConsts.ADD_ELEMENT, imgTarget);
					}
				}
			}
		});

		table = new Table(parent, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {

				if (arg0.detail == SWT.CHECK) {
					eventBroker.send(MyEventConsts.HIDE_ELEMENT, ipm.getIndexToChange(arg0));
				}
			}
		});

		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
	}

	@Focus
	public void setFocus() {
		table.setFocus();
	}

}
	

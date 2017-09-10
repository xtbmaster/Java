package rcp.views;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;

public class ImgList {

	public ImgList() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new FormLayout());
		
		Composite composite = new Composite(parent, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 64);
		fd_composite.right = new FormAttachment(0, 127);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new GridLayout(1, false));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("New Button");
		
		List list = new List(parent, SWT.BORDER);
		FormData fd_list = new FormData();
		fd_list.bottom = new FormAttachment(100, -10);
		fd_list.right = new FormAttachment(composite, 0, SWT.RIGHT);
		fd_list.top = new FormAttachment(composite, 4);
		fd_list.left = new FormAttachment(0);
		list.setLayoutData(fd_list);
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO	Set the focus to control
	}
}

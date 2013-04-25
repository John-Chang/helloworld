package com.zzj.learn;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;

public class SWTTest {

	protected Shell shell;
	Tree tree;

	private Action add, update, refresh;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SWTTest window = new SWTTest();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FormLayout());
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0, 32);
		composite.setLayoutData(fd_composite);

		tree = new Tree(composite, SWT.BORDER);
		FormData fd_tree = new FormData();
		fd_tree.top = new FormAttachment(0, 10);
		tree.setLayoutData(fd_tree);

		TreeItem item = new TreeItem(tree, SWT.None);
		item.setText("afd");

		TreeItem item2 = new TreeItem(item, SWT.None);
		item.setText("afd");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		fd_tree.left = new FormAttachment(12, 3);
		scrolledComposite.setLayoutData(new FormData());
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new FormData());
		tree.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ininMenu();// 出始化菜单
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void ininMenu() {
		if (tree.getSelection() != null) {
			TreeItem item = tree.getSelection()[0];
			if (item != null) {
				String name = (String) item.getText();
				if (name.equals("afd")) {
					initializeMenu2();
				} else if (name.equals("王五")) {
					initializeMenu2();
				} else {
					initializeMenu();
				}
			}
		}
	}

	private void initializeMenu() {
		createActions();
		MenuManager mgr = new MenuManager();
		mgr.add(add);
		mgr.add(update);
		mgr.add(refresh);
		Menu menu = mgr.createContextMenu(tree);
		tree.setMenu(menu);
	}

	/**
	 * 初始化菜单1
	 */
	private void initializeMenu1() {
		createActions();

		MenuManager mgr = new MenuManager();
		mgr.add(update);
		mgr.add(refresh);
		Menu menu = mgr.createContextMenu(tree);
		tree.setMenu(menu);
	}

	/**
	 * 初始化菜单2
	 */
	private void initializeMenu2() {
		createActions();

		MenuManager mgr = new MenuManager();
		mgr.add(add);
		mgr.add(refresh);
		Menu menu = mgr.createContextMenu(tree);
		tree.setMenu(menu);
	}

	private void createActions() {
		add = new Action("新增") {
			public void run() {
				// .........
			}
		};
		update = new Action("修改") {
			public void run() {
				// .........
			}
		};
		refresh = new Action("刷新") {
			public void run() {
				// .........
			}
		};
	}
}

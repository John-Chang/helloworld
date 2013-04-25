package com.zzj.learn;

import java.awt.print.Printable;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


public class TestTreeAndExecutionDemo  {
	protected Shell shell;

	public Composite composite;
	public Composite bottomComposite;
	
	private Tree tree;
	private Action add, update, refresh;//增加，修改，刷新
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		Display display = Display.getDefault();
//		Shell shell = new Shell(display);
//		shell.setText("tree test");
//		shell.setSize(800,600);
//		shell.open();
//		while (!shell.isDisposed()){
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//		display.dispose();
		
		TestTreeAndExecutionDemo testTree = new TestTreeAndExecutionDemo();
		testTree.open();
	}

//	/**
//	 * Open the window.
//	 */
	public void open() {
		Display display = new Display();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
//
//	/**
//	 * Create contents of the window.
//	 * @wbp.parser.entryPoint
//	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(800, 600);
		shell.setText("Jtester Configurations");
		composite = new Composite(shell, SWT.None);
		composite.setLayout(new FillLayout());
		Button button = new Button(composite, SWT.None);
		tree = new Tree(composite, SWT.None);
		TreeItem item1 = new TreeItem(tree, SWT.None);
		item1.setText("item1");
		tree.setVisible(true);
		
	}
//	
//	
//	
//	
//	public TestTree() {
//		// TODO Auto-generated constructor stub
//		
////		tree.addMouseListener(new MouseAdapter() {
////			public void mouseDown(MouseEvent e) {
////			ininMenu();// 出始化菜单
////			}
////			});
//	}
//	
//	
//	/**
//	 * 要显示的菜单
//	 */
//	private void ininMenu() {
//	TreeItem item = tree.getSelection()[0];
//	if (item != null) {
//	String name = (String) item.getData();
//	if (name.equals("张三")) {
//	initializeMenu1();
//	} else if (name.equals("王五")) {
//	initializeMenu2();
//	} else {
//	initializeMenu();
//	}
//	}
//	}
//
//	private void createActions() {
//	add = new Action("新增") {
//	public void run() {
//	// .........
//	}
//	};
//	update = new Action("修改") {
//	public void run() {
//	// .........
//	}
//	};
//	refresh = new Action("刷新") {
//	public void run() {
//	// .........
//	}
//	};
//	}
//
//	/**
//	 * 初始化菜单
//	 */
//	private void initializeMenu() {
//	MenuManager mgr = new MenuManager();
//	mgr.add(add);
//	mgr.add(update);
//	mgr.add(refresh);
//	Menu menu = mgr.createContextMenu(tree);
//	tree.setMenu(menu);
//	}
//
//	/**
//	 * 初始化菜单1
//	 */
//	private void initializeMenu1() {
//	MenuManager mgr = new MenuManager();
//	mgr.add(update);
//	mgr.add(refresh);
//	Menu menu = mgr.createContextMenu(tree);
//	tree.setMenu(menu);
//	}
//
//	/**
//	 * 初始化菜单2
//	 */
//	private void initializeMenu2() {
//	MenuManager mgr = new MenuManager();
//	mgr.add(add);
//	mgr.add(refresh);
//	Menu menu = mgr.createContextMenu(tree);
//	tree.setMenu(menu);
//	}
//}
	
	void method(int b){
		int c = 12 + b;
		int d = c;
		c = d - b;
		if(c>0)
			System.out.print("ok");
		else {
			System.out.print("no");
		}
	}

}
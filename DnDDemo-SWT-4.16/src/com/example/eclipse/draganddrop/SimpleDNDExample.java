package com.example.eclipse.draganddrop;

import java.io.IOException;
import java.nio.file.Paths;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class SimpleDNDExample {

	public static void main(final String[] args) throws IOException {

		final var builder = new ProcessBuilder(Paths.get("HelloWorldCSharpApplication.exe").toString());
		final var process = builder.start();

		final var applicationShell = new Shell();
		applicationShell.setLayout(new FillLayout());

		// Create the tree and some tree items
		final var simpleSWTTree = new Tree(applicationShell, SWT.NONE);
		final var treeItem = new TreeItem(simpleSWTTree, SWT.NONE);
		treeItem.setText("Item 1");

		// Create the drag source on the tree
		final var dragSource = new DragSource(simpleSWTTree, DND.DROP_COPY);
		dragSource.setTransfer(TextTransfer.getInstance());
		dragSource.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragSetData(final DragSourceEvent dragEvent) {
				// Set the data to be the first selected item's text
				dragEvent.data = simpleSWTTree.getSelection()[0].getText();
			}
		});

		applicationShell.pack();
		applicationShell.open();
		final var display = Display.getDefault();
		while (!applicationShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		process.destroy();
	}
}

# Eclipse 4.13 Drag and Drop Issue
Repo for provinding examples for the drag and drop issue between Java and CSharp based applications introduced in Eclipse 4.13 SDK's SWT libraries.

## Introduction
We have the responsibility to develop a workflow where content is dragged and dropped from a SWT based application to an application developed in CSharp(WPF application based on .NET Framework 4.7.2).

Recently we were upgrading from Eclipse 4.7.3 to Eclipse 4.16 where we ran into a drag and drop issue between the two application. Intermittenly but frequently when dragging an object(string) from the SWT application to the CSharp application, SWT application crashes.

## Experiment
To develop a simple SWT application with a DragSource and develop a simple CSharp application with a Text field(with allow drop property set to true) where the objects from the SWT application can be dropped.

### Workflow 1 (Normal desired workflow)
1. Launch the application utilizing org.eclipse.swt.win32.win32.x86_64_3.111.0.v20190605-1801(Taken from Eclipse SDK 4.12). This should launch 2 windows
  a. The SWT application with a simple tree and an item that can be dragged and dropped.
  b. The CSharp application that contains a text area where the item from the SWT application can be dropped.
2. Drag and drop the item from the SWT application to the CSharp application and repeat as many times as possible and **no** crash is observed.

### Workflow 2 (Workflow where the crash is Observed)
1. Launch the application utilizing org.eclipse.swt.win32.win32.x86_64_3.112.0.v20190904-0609(Taken from Eclipse SDK 4.13). This should launch 2 windows
  a. The SWT application with a simple tree and an item that can be dragged and dropped.
  b. The CSharp application that contains a text area where the item from the SWT application can be dropped.
2. Drag and drop the item from the SWT application to the CSharp application and repeat as many times as possible and intermittenly but frequently a crash is observed.

## Theory behind the crash

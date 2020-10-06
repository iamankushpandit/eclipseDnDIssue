# Eclipse 4.13 Drag and Drop Issue
Repo for provinding examples for the drag and drop issue between Java and CSharp based applications introduced in Eclipse 4.13 SDK's SWT libraries.

## Introduction
We have the responsibility to develop a workflow where content is dragged and dropped from a SWT based application to an application developed in CSharp(WPF application based on .NET Framework 4.7.2).

Recently we were upgrading from Eclipse 4.7.3 to Eclipse 4.16 where we ran into a drag and drop issue between the two application. Intermittenly but frequently when dragging an object(string) from the SWT application to the CSharp application, SWT application crashes.

## Experiment
To develop a simple SWT application with a DragSource and develop a simple CSharp application with a Text field(with allow drop property set to true) where the objects from the SWT application can be dropped.

## Setup
1. Import the [DnDDemo-SWT-4.12](https://github.com/iamankushpandit/eclipseDnDIssue/tree/master/DnDDemo-SWT-4.12) and [DnDDemo-SWT-4.13](https://github.com/iamankushpandit/eclipseDnDIssue/tree/master/DnDDemo-SWT-4.13) in to an eclipse workspace. For this experiment Eclipse IDE for RCP and RAP Developers (includes Incubating components)Version: 2020-06 (4.16.0) Build id: 20200615-1200 was used.
2. Import the [HelloWorldCSharpApplication](https://github.com/iamankushpandit/eclipseDnDIssue/tree/master/HelloWorldCSharpApplication) application on Visual Studio. For this experiment Visual Studio 2019 was used. This is just for the reference as the compiled version of the executable is already included in the DnDDemo-SWT-4.12 and DnDDemo-SWT-4.13 projects.
3. The code on DnDDemo-SWT-4.12 and DnDDemo-SWT-4.13 is exactly the same but the refereced SWT library is different.

### Workflow 1 (Normal desired workflow)
1. Launch the DnDDemo-SWT-4.12 application utilizing org.eclipse.swt.win32.win32.x86_64_3.111.0.v20190605-1801(Taken from Eclipse SDK 4.12). This should launch 2 windows
  a. The SWT application with a simple tree and an item that can be dragged and dropped.
  b. The CSharp application that contains a text area where the item from the SWT application can be dropped.
2. Drag and drop the item from the SWT application to the CSharp application and repeat as many times as possible and **no** crash is observed.

### Workflow 2 (Workflow where the crash is Observed)
1. Launch the DnDDemo-SWT-4.13 application utilizing org.eclipse.swt.win32.win32.x86_64_3.112.0.v20190904-0609(Taken from Eclipse SDK 4.13). This should launch 2 windows
  a. The SWT application with a simple tree and an item that can be dragged and dropped.
  b. The CSharp application that contains a text area where the item from the SWT application can be dropped.
2. Drag and drop the item from the SWT application to the CSharp application and repeat as many times as possible and intermittenly but frequently a crash is observed.

## Possible Cause behind the crash
Changes introduced to DragSource in SWT 4.13 for [Bug 549643](https://bugs.eclipse.org/bugs/show_bug.cgi?id=549643). This can be seen when the DragSource source code is compared between 4.12 and 4.13 SDK.

## Bug submission, analysis and actions
We have logged a bug request through Eclipse Platform's bugzilla here [Bug-567422](https://bugs.eclipse.org/bugs/show_bug.cgi?id=567422). Please follow the bug for analysis and other information and observations provided. To follow the bug(Eclipse account needed) by click on CC List check box on the top right followed by clicking the Save Changes button on the top right header section.

## Resolution
The aggressive release of all COM objects after the drag/drop operation is done can be problematic if the drop target keeps a reference to one of the COM objects for later use. When the target later tries to release (or access) the already disposed COM object a crash from invalid memory access is likely. Instead if should trust that the target will eventually release the references it still holds. For the case that the target will request the data again after DND is finished the last requested data is cached. Note: sending a DragSetData event before DragStart or after DragEnd is considered a bug.

[Bug-567422](https://bugs.eclipse.org/bugs/show_bug.cgi?id=567422) is now resolved and will be available with Eclipse 4.18 SDK.

# eclipseDnDIssue
Repo for provinding examples for the drag and drop issue between Java and CSharp based applications introduced in Eclipse 4.13 SDK's SWT libraries.

## Introduction
We have the responsibility to develop a workflow where content is dragged and dropped from a SWT based application to an application developed in CSharp(WPF application based on .NET Framework 4.7.2).

Recently we were upgrading from Eclipse 4.7.3 to Eclipse 4.16 where we ran into a drag and drop issue between the two application. Intermittenly but frequently when dragging an object(string) from the SWT application to the CSharp application, SWT application crashes.

## Experiment
To develop a simple SWT application with a DragSource and a DragTarget and also develop a simple Csharp application with a Text field(with allow drop property set to true) where the objects from the SWT application can be dropped.

### Workflow

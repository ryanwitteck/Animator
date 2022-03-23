# Animator

CS3500 Assignment 4

For this project, we used the MVC model to break down aspects of our code:

Controller: We created an interface representing commands that can be applied to any object, whether
it be a command that occurs instantly or that occurs over a length of time. These aspects represent
the interface and abstract classes, denoted by ICommand, GradualCmd, and InstantCmd. We have
implemented various other commands, such as AddShapeCmd, MoveCmd, PlaceCmd, and ResizeCmd. More will
be built in the future and as we come across new commands in creating this project.

Model: Our model has various aspects that will represent the data behind our animation. It has been
broken down into 3 components: Attributes, ObjectInterfaces, and Shapes. Our attributes package
represents our smallest and simplest data: Color and Posn, which represents a shapes's color and
position at any given time. The ObjectInterfaces package connects with the controller as it
represents different types of commands that can be made. They are categorized as: Drawables,
Moveables, or Scalables. More may be added if new commands are created. The final package, Shapes,
represent objects that can be placed on our canvas, as well as Frames that will represent our actual
animation when the time comes. These classes combine our other data, such as commands, and our
attributes package classes, and is able to represent the state of our animation at any given moment.

View: Under construction
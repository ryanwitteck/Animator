<div id="top"></div>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->

<!-- PROJECT LOGO -->
<br />

<h3 align="center">Animator</h3>

  <p align="center">
    CS3500 Assignment 4-6 Spring 2022
  </p>

  <p align="center">
    Authors: Charles Zhao, Ryan Witteck
  </p>

<!-- ABOUT THE PROJECT -->
## Our MVC Model

In this project, we used the MVC model to design and create an implementation of an animation using
basic shapes. The traditional roles a controller fills is split between our view and main classes 
for non-interactive views. While for interactive views, the controller roles are split between our 
view, main, and UI classes.

### Model

Our model has various aspects that will represent the data behind our animation. It has been
broken down into 5 components: Attributes, Shapes, Commands, Frames, and Animations.

#### Attributes
Attributes are the smallest and simplest data that exists: Color and Posn, which represent a shape's
color and position at any given time. These are implemented in our attributes package. 

In part two of this assignment, we changed our Color class to store RGB values as doubles instead of 
ints to allow us to still accurately represent colors when they are not exact integer values. We 
also created a new method changeColor that allows us to change the RGB values of a color by a 
certain amount instead of directly setting it to a value. We created this method, so we could more 
easily implement our ChangeColorCmd class. We have also changed the toString methods of both the 
Posn and Color classes in order to make the information more easily parsable.

#### Shapes
Shapes are the objects being animated by our model and are represented by the interface IShape.
IShape itself extends our three "basic" interfaces Drawable, Movable, and Scalable. These interfaces
exist to clearly divide the three basic types of information each IShape should contain and actions
each IShape should be able to perform -- i.e. every shape should contain the information necessary
to define its own image (Drawable), have a position and be to change its position (Movable), and be 
able to stretch or shrink in both the x and y dimensions (Scalable). Currently, we have four shape 
classes: BasicShape, Rectangle, Oval, and Compound Shape. The BasicShape class is an abstract class
that the other three extend, contains fields shared by all shapes, and implements methods that do 
not differ in behavior depending on the shape type. Our Oval and Rectangle classes represent 
ellipses and rectangles, respectively, and our model builder and views are able to create and 
render these two shapes. The CompoundShape class only exists as an example of a more complex 
implementation of shape and is not properly implemented to work with our model, builder, or view.

In part two of this assignment, we removed the shrink and scale up/down methods from the Scalable 
interface and their implementations and added the methods addHorizontal and addVertical to Scalable.
The removed methods didn't serve any particular purpose and were unlikely to serve any in the 
future. Meanwhile, the added methods are being used to more easily implement the ResizeCmd.

No other major changes have been made that to any IShape implementations that were not a result of
changing the design of the Scalable interface.

#### Commands
Commands are function-objects that define the behavior of an animation. All actions from creating a
shape in an animation, moving a shape in an animation, to changing a shape's color, et cetera should
be done through a command. Commands are represented by the ICommand interface and all common fields 
and implementations are implemented by the abstract class ACommand. ICommands are split into two 
types: InstantCmds and GradualCmds, two abstract classes representing commands that occur instantly 
and commands that occur over some period of time, respectively. Most commands should take the start 
state(s) of the attribute(s) they are changing and throw an error if the attribute they are 
targeting is an unexpected value. Some commands such as our AddOvalCmd and AddRectCmd do not throw 
exceptions themselves, instead relying on our IAnimation implementation to throw an exception in 
case they fail.

We have implemented the classes AddOvalCmd, a command that adds an Oval/Ellipse to an animation; 
AddRectCmd, a command that adds a Rectangle to an animation; ChangeColorCmd, a command that changes
the color of some object over a period of time; MoveCmd, a command that moves an object from one 
position to some other position over a period of time; PlaceCmd, a command that instantly sets an 
object's position to some position; RemoveDrawableCmd, a command that removes an object from an 
animation; and ResizeCmd, a command that changes the dimensions of an object over a period of time.

In part two of this assignment, we changed the execute method to take in an IAnimation as a
parameter, changed our command classes to store the name of their targets rather than a pointer to 
their target, and changed the format of the strings returned by our cmdLog method. The first two 
changes were made mainly because the previous model of ICommand ran into issues when we implemented 
our new methods in our animation model among other issues due to not following proper coding 
conventions. We changed the cmdLog method to be more easily parsable.

Besides, these foundational changes to our ICommand implementations, the biggest changes made in 
part two have been the implementation of all the new ICommand classes themselves. The previous 
AddShapeCmd has been split into AddOvalCmd and AddRectCmd, ResizeCmd is no longer abstract and is
fully implemented, and the classes ChangeColorCmd and RemoveDrawableCmd are completely new.

#### Frames
Frames represent the state of an animation at a single moment in time i.e. a single frame in an
animation. Frames provide the necessary information to visualize an animation at a given moment.
Frames are represented by the IFrame interface and implemented in the Frame class. Frames are the 
main building blocks of our visual view implementation.

There have been no changes to our implementation of the IFrame interface or the Frame class since
part one of this assignment.

#### Animations
Animations are our model of an animated video itself. They are defined as a sequence of frames and a
sequence of commands. Animations are represented by our IAnimation interface and implemented by
the AAnimation (an abstract class) and SimpleAnimation classes. An animation allows the user to
access the information necessary to visualize the animation itself either as a video or as a
sequence of commands.

In part two of this assignment, we renamed our implementation of IAnimation to SimpleAnimation and 
removed multiple redundant methods that offered different ways of getting the frames that model 
this animation. We also added new methods to add and remove commands from our animation; add 
and remove objects from our animation; set and get the window dimensions of our animation; get the 
commands in this animation; and compile and recompile this animation. These new methods have made 
our model more flexible and give it a greater ability to provide our views with the information 
they need.

Since part two of this assignment, we have only made no changes to our implementation of the model
except for one bug fix. The bug was that the model sometimes created new IFrames with the list of 
the objects in the IFrames not being in the correct order, which could affect our visual and 
interactive views. This has been fixed.

### View

In this project, the view is takes on the controller's role of timing and provides the user with
some visualization of some animation represented by our model. A visualization of an animation is
represented by the interface AnimationView. To receive a visualization of an animation, the 
renderAnimation method must be called by the user. We have implemented three types of views that 
directly implement the AnimationView interface: text, visual, and SVG.

Since part two of this assignment, we have created a new view interface InteractiveView. This 
interface extends our AnimationView interface and represents a view that the user can interact with 
as the animation is playing. The interface allows the user to control the playback options of the 
view. These options include:

-Pausing/Unpausing the animation

-Restarting the animation

-Changing the framerate

-Toggling whether the animation loops after completion

#### Text
Our text view of an animation produces a readable text visualization of an animation. We have 
implemented this view in the TextView class. This class requires the user to supply an IAnimation 
model, a valid data destination for the view to print the created visualization to, and a framerate. 
This visualization printed by this view is the command log of the animation being viewed except 
that rather than measuring time in ticks, time is measured in seconds.

#### Visual
Our visual view of an animation creates an animated video of an animation model using swing. We have
implemented this view in the VisualView class. This class requires the user to supply a window 
title, IAnimation, and frame rate in ticks per second. After the renderAnimation method is called, 
a window will appear, on which the animation will play. This class relies on the class 
SwingViewPanel a JPanel, which is responsible for drawing each frame of the animation. After the 
animation finishes running, the window will not close automatically, and if you would like to end 
the animation early, simply close the window.

We have not implemented the ability to scroll through the window yet.

#### SVG
Our SVG view of an animation produces a text representation of our animation that is compliant 
with the XML-based SVG file format. This view allows the user to convert their animations to svg 
files which can be run in most browsers. This view is implemented in our SvgView class, which must 
be supplied with an IAnimation model, output destination, and frame rate to function. Our 
implementation of this view relies on parsing the command logs of the ICommands in the given 
IAnimation to produce the SVG visualization.

#### Interactive
Our interactive view is implemented in the class BasicInteractiveView. Our implementation creates an
animated video of an animation model using swing. The visuals provided are identical to those 
provided by our visual view. In addition to the having all the functionality of the visual view, 
this interface also defines methods that can be used by our controller class to control the playback
options of this view. Like our other views, this view will automatically start playing when 
the renderAnimation method is called.

### Controller and IO

In this project, the controller's role of parsing user input is filled by our Main class and the
classes in the IO package. They are in charge of parsing user inputs and building a model and view
according to the given input.

For interactive views, we have implemented a new interface InteractiveUI. This interface takes on 
a mix of traditionally controller and view roles. The InteractiveUI interface contains one method 
start that renders a user-friendly UI that the user can use to control the playback behavior of an 
interactive view.

#### IO Package
This package contains an interface TweenModelBuilder and two classes AnimationFileReader and 
OurModelBuilder (an implementation of TweenModelBuilder). The classes AnimationFileReader and 
OurModelBuilder together are used to create animations based off of an appropriately formatted text
file. The AnimationFileReader class is responsible for parsing the text in the given file into 
commands that OurModelBuilder can recognize and OurModelBuilder translates uses that information to 
create an IAnimation by adding various ICommands to it.

#### Main
Our main class and method is responsible for reading the command line inputs given by the user and 
parsing that into five pieces of information: the name of the animation file, the type of view 
desired, the output file, and the tick rate of the animation. Using that information, our main 
method uses an instance of AnimationFileReader and OurModelBuilder to read the given animation file
and create the appropriate model and view of it.

#### InteractiveUI
This interface was created to handle creating and managing the UI for interactive views. It contains
one method start. When called, this method should render the view the UI is connected to and 
display some UI that the user can use to control the playback of the view.

We have implemented one type of InteractiveUI in our SwingGUI class. This class creates a new 
window containing a Play/Pause button, Reset button, Looping checkbox, and FPS text-field that can 
be used to control the playback of any complete implementation of our InteractiveView interface. To
pause or unpause the animation, simply push the button labeled "Play/Pause"; to restart the 
animation, simply push the button labeled "Restart"; to toggle looping, simply check or uncheck the
checkbox labeled "Loop Animation"; and to set the framerate, enter a number into the text-field 
labeled "fps:".

To make the controls more user-friendly, the label next to the looping checkbox will
end in "(on)" or "(off)" to clearly display whether the animation is looping, and the fps text-field 
will always display the current framerate, so that the user will always know the framerate that the
animation is running in and if they input an unrecognized or invalid framerate. For example, if the 
user tries to set the framerate to a non-integer, negative integer, or some other unsupported 
input, neither the framerate of the animation nor the framerate displayed by the text-field will 
change.

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
    CS3500 Assignment 4-5 Spring 2022
  </p>

  <p align="center">
    Authors: Charles Zhao, Ryan Witteck
  </p>

<!-- ABOUT THE PROJECT -->
## Our MVC Model

In this project, we used the MVC model to design and create an implementation of an animation using
basic shapes. The traditional roles a controller fills is split between our view and main class.

### Model

Our model has various aspects that will represent the data behind our animation. It has been
broken down into 5 components: Attributes, Shapes, Commands, Frames, and Animations.

#### Attributes
Attributes are the smallest and simplest data that exists: Color and Posn, which represent a shape's
color and position at any given time. These are implemented in our attributes package.

#### Shapes
Shapes are the objects being animated by our model and are represented by the interface IShape.
IShape itself extends our three "basic" interfaces Drawable, Movable, and Scalable. These interfaces
exist to clearly divide the three basic types of information each IShape should contain and actions
each IShape should be able to perform -- i.e. every shape should contain the information necessary
to define its own image (Drawable), be able to change position (Movable), and be able to be
stretched or shrunk in both the x and y dimensions (Scalable). Currently, three shapes have been
implemented: Rectangle, Oval, and Compound Shape. The CompoundShape class exists only as an
example of a more complex implementation of shape. Our model builders only allow users to create
Rectangles and Ovals in the current implementation.

#### Commands
Commands are function-objects that define the behavior of an animation. All actions from creating a
shape in an animation, moving a shape in an animation, to changing a shape's color, et cetera should
be done through a command. Commands are represented by the ICommand interface and are split into two
categories: InstantCmds and GradualCmds, representing commands that occur instantly and commands
that occur over some period of time. Most commands should take the start state(s) of the
attribute(s) they are changing and throw an error if the attribute they are targeting is an
unexpected value. Some commands such as our addOvalCmd and addRectCmd do not throw exceptions
themselves, instead relying on our IAnimation implementation to throw an exception in case they
fail.

#### Frames
Frames represent the state of an animation at a single moment in time i.e. a single frame in an
animation. Frames provide the necessary information to visualize an animation at a given moment.
Frames are represented by the IFrame interface and implemented in the Frame class. Frames are the
main building blocks of our visual view implementation.

#### Animations
Animations are our model of an animated video itself. They are defined as a collection frames and a
collection of commands. Animations are represented by our IAnimation interface and implemented by
the AAnimation (is an abstract class) and SimpleAnimation classes. An animation allows the user to
access the information necessary to visualize the animation itself either as a video or as a
sequence of commands.

### View

In this project, the view is takes on the controller's role of timing and provides the user with
some visualization of some animation represented by our model. A visualization of an animation is
represented by the interface AnimationView. To get receive the visualization, the renderAnimation
method must be called by the user. We have implemented three types of views: text, visual, and SVG.

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

#### SVG
Our SVG view of an animation produces a text representation of our animation that is compliant
with the XML-based SVG file format. This view allows the user to convert their animations to svg
files which can be easily run. This view is implemented in our SvgView class, which must be supplied
with an IAnimation model, output destination, and frame rate to function. Our implementation of
this view relies on parsing the command logs of the ICommands in the given IAnimation to produce
the SVG visualization.

### Controller and IO

In this project, the controller's role of parsing user input is filled by our Main class and the
classes in the IO package. They are in charge of parsing user inputs and building a model and view
according to the given input.

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
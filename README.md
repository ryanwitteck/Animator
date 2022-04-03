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
    CS3500 Assignment 4 Spring 2022
    <br />
  </p>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents (Links not working. Sorry)</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li><a href="#Usage">Usage</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

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
exist to clearly divide the three basic type of information each IShape should contain and actions
each IShape should be able to perform -- i.e. every shape should contain the information necessary
to define its own image (Drawable), be able to change position (Movable), and be able to be
stretched or shrunk in both the x and y dimensions (Scalable). Currently, only three shapes have
been implemented: Rectangle, Oval, and Compound Shape and only Rectangle and Oval are in use.

#### Commands
Commands are function-objects that define the behavior of an animation. All actions from creating a
shape in an animation, moving a shape in an animation, to changing a shape's color, et cetera should
be done through a command. Commands are represented by the ICommand interface and are split into two
categories: InstantCmds and GradualCmds, representing commands that occur instantly and commands
that occur over some period of time.

#### Frames
Frames represent the state of an animation at a single moment in time i.e. a single frame in an
animation. Frames provide the necessary information to visualize an animation at a given moment.
Frames are represented by the IFrame interface and implemented in the Frame class.

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
Our text view of an animation gives a readable visualization of an animation. We have implemented
this view in the TextView class. This class requires the user to create an IAnimation and supply a
valid data destination for the view to print the created visualization to. Currently, this view
simply prints the command log of an animation to the given destination when renderAnimation is run.

#### Visual
Our visual view of an animation creates an animated video of an animation using swing. We have
implemented this view in the VisualView class, which relies on the SwingViewPanel class. This class
requires the user to supply a window title, IAnimation, and frame rate in ticks per second. After
the renderAnimation method is called, the animation will play and loop infinitely. To end the video,
simply close the window.

#### SVG
We have not completed the implementation of this view yet. When complete this view will use the
information provided by our model to create a svg file that can be played as an animated video.

### Controller and IO

In this project, the controller's role of parsing user input is filled by our Main class and the
classes in the IO package. They are in charge of parsing user inputs and building a model and view
according to the given input.

#### Main
Our main class and method parse

<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

<p align="right">(<a href="#top">back to top</a>)</p>
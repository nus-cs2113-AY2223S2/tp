# Feature : *Performing a workout*

## About

Our workouts feature is designed to help you complete a workout and log your progress.


## Commands

### Viewing your current workout exercises ```current```

Displays a list of the user's current workout exercises.

Format: ```current```

For example, if the current workout session started with the following workouts: 
```
Exercise ID: 0. 
Name: 3/4 Sit-Up
Difficulty Level: beginner
Workout Type: core
Lie down on the floor and secure your feet. Your legs should be bent at the knees., Place your hands behind or to the side of your head. You will begin with your back on the ground. This will be your starting position., Flex your hips and spine to raise your torso toward your knees., At the top of the contraction your torso should be perpendicular to the ground. Reverse the motion, going only Â¾ of the way down., Repeat for the recommended amount of repetitions.

Exercise ID: 828. 
Name: Tuck Crunch
Difficulty Level: beginner
Workout Type: core
To begin, lie down on the floor or an exercise mat with your back pressed against the floor. Your upper body should be lying across your sides with the palms facing down., Your legs should be crossed by wrapping one ankle around the other. Slowly elevate your legs up in the air until your thighs are perpendicular to the floor with a slight bend at the knees. Note: Your knees and toes should be parallel to the floor as opposed to the thighs., Move your upper body from the floor and cross them so they are resting on your upper body. This is the starting position., While keeping your upper body pressed against the floor, slowly lift your torso. Remember to exhale while perform this part of the exercise., Slowly begin to lower your torso back down to the starting position while inhaling., Repeat for the recommended amount of repetitions.
```

Typing the input ```current``` would correspond to the following output, which just lists out the current workout's details:
````agsl
Exercise ID: 0. 
Name: 3/4 Sit-Up
Difficulty Level: beginner
Workout Type: core
Lie down on the floor and secure your feet. Your legs should be bent at the knees., Place your hands behind or to the side of your head. You will begin with your back on the ground. This will be your starting position., Flex your hips and spine to raise your torso toward your knees., At the top of the contraction your torso should be perpendicular to the ground. Reverse the motion, going only Â¾ of the way down., Repeat for the recommended amount of repetitions.

Exercise ID: 828. 
Name: Tuck Crunch
Difficulty Level: beginner
Workout Type: core
To begin, lie down on the floor or an exercise mat with your back pressed against the floor. Your upper body should be lying across your sides with the palms facing down., Your legs should be crossed by wrapping one ankle around the other. Slowly elevate your legs up in the air until your thighs are perpendicular to the floor with a slight bend at the knees. Note: Your knees and toes should be parallel to the floor as opposed to the thighs., Move your upper body from the floor and cross them so they are resting on your upper body. This is the starting position., While keeping your upper body pressed against the floor, slowly lift your torso. Remember to exhale while perform this part of the exercise., Slowly begin to lower your torso back down to the starting position while inhaling., Repeat for the recommended amount of repetitions.

````

### Cancelling a workout and return to main menu ```cancel```

Cancels the user's current workout session. The workout session is recognised as not completed.

Format: ```cancel```

Calling the ```cancel``` command here will give the following output:
<br>
<br>
```Workout cancelled, you can complete it next time!```

### Finishing a workout ```finish```

Finishes the current workout session. The workout session is recognised as completed.

Format: ```finish```

For example, let the current workout include the exercises as used by the example above (i.e. 3/4 sit up + Tuck Crunch)

Calling the ```finish``` command will provide the following outputs:
1) Workout Completed Message: <br>```Workout completed! Congratulations on your hard work!```<br><br>
2) (If any achievements are accomplished) <br> Prints out a congratulations message on completing 1 or more achievements 
<br> ```Congradulations! You have achieved the following achievements:```<br><br>
3) (If any achievements are accomplished) <br> Prints out the list of achievements achieved upon completion 
of a specific exercise session
e.g: <br>
```
1)  Easy Lemon DESTROYEERRR: Complete 5 easy exercises!
2) The Static Warrior: Complete 5 Static workouts!
``` 

4) (If any achievements are accomplished) <br> Prints out a motivating message to further motivate the user to continue
using Fitness Duke <br>
```Keep on working out with Fitness Duke!```
# Feature : *Planner*

## About

Our planner feature is designed to help you plan your weekly workout schedule in order to achieve your workout goals.

## Commands

### Viewing plans ```plans```

Displays all workout plans created by the user from Monday to Sunday.

```
YOUR WORKOUT PLAN:
_________
MONDAY
1. home_leg_day
Filters: legs static
_________
TUESDAY
_________
WEDNESDAY
1. gym_with_tom
Filters: gym upper lower hard
_________
THURSDAY
_________
FRIDAY
1. tabata_with_ben
Filters: static hard
_________
SATURDAY
1. chill_day
Filters: easy static
_________
SUNDAY
________________________________________
```

Format: ```plans```

### Adding a plan ```add [day] [plan_name] [arg 1] [arg 2] [arg 3] ```

Creates a new workout plan using existing filters specified by the user, for a specific day of the week.

Format: ```add monday home_legs_day easy static legs```
```
===>Planner Mode<===
add monday home_legs_day easy static legs
[home_legs_day] added to MONDAY

===>Planner Mode<===
```

### Deleting a plan ```delete```

Deletes an existing workout plan on a specified day of the week.

Format: ```delete monday home_legs_day```
```
===>Planner Mode<===
delete monday home_legs_day
[home_legs_day] deleted from MONDAY

===>Planner Mode<===
```

### Exiting the planner ```exit```

Exits the workout planner and returns to main program.

Format: ```exit```
```
===>Planner Mode<===
exit
Exited planner editor!
________________________________________
```
### Viewing help ```help ```

Shows a list of commands that the user can input while in the workout planner feature

Format: ```help```
```
===>Planner Mode<===
help
These are some commands available: 
[add]
	Create a new plan on a day of the week: add monday plan_name FILTER1 FILTER2 ... x
	FILTER stands for a specific requirement you want to include in your exercise
[delete]
	delete a plan on a day of the week: delete monday plan_name
[plans]
	Show all plans
[filters]
	View all available filters
[exit]
	Exit workout plan editor

===>Planner Mode<===
```
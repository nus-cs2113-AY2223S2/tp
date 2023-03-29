<!-- ---
layout: page
title: User Guide
--- -->
<h1> User Guide </h1>
<h2> Introduction </h2>
RM(recipe manager) is a <strong>desktop recipe manager application for managing recipes, optimized for use via a Command Line Interface</strong>(CLI). 
<h2> Table of Contents </h2>

* **[Quick Start](#quick-start)**
* **[Features](#features)**
    * **[Viewing help：`help`](#viewing-help-help)**
    * **[Adding a recipe: `add`](#adding-a-recipe-add)**
    * **[Editing steps for a recipe: `editstep`](#editing-a-recipe-step-editstep)**
    * **[Editing ingredients for a recipe: `editingredient`](#editing-a-recipe-ingredient-editingredient)**
    * **[Deleting a recipe: `delete`](#deleting-a-recipe-delete)**
    * **[Finding recipes: `find`](#finding-recipes-find)**
    * **[Viewing a recipe: `view`](#viewing-a-recipe-view)**
    * **[Listing all recipes: `list`](#listing-all-recipes-list)**
    * **[Clearing all entries: `clear`](#clearing-all-entries-clear)**
    * **[Exiting the program: `exit`](#exiting-the-program-exit)**
    * **[Saving the data](#save-data)**
    * **[Editing the data file](#edit-data)**
    <!-- * **[Editing the data file](#edit-data)** -->
<!-- * **[Upcoming features(v2.0)](#upcoming-features)**
    * **[Using a recipe: `use`](#using-a-recipe-use)**
        * **[Moving to next step: `next`](#moving-to-next-step-next)**
        * **[Moving to previous step: `prev`](#moving-to-previous-step-prev)**
        * **[Moving to a specific step: `goto`](#moving-to-a-specific-step-goto)**
        * **[Marking a step as done: `done`](#marking-a-step-as-done-done)** -->
* **[FAQ](#faq)**
* **[Command Summary](#command-summary)**
<h2 id="quick-start"> Quick Start</h2>

1. Enusure you have `Java 11` installed on your Computer.
2. Download the latest `RM.jar` from here.
3. Copy the file to the folder you want to use as home folder for the recipe manager.
4. Use `Win+R` to open the command prompt and type `cmd` and press Enter.
5. Then `cd` into the folder where you copied the jar file. e.g. `cd C:\Users\Lee\Desktop\MyRecipe`
6. Type `java -jar RM.jar` and press Enter to start the program.
<h2 id="features"> Features</h2>
<ol>
<li>
Words in <strong>UPPER_CASE</strong> are the parameters to be supplied by the user.
    
e.g. in <code class="language-plaintext highlighter-rouge">add DESCRIPTION</code>, <code class="language-plaintext highlighter-rouge">DESCRIPTION</code> is a parameter which can be used as <code class="language-plaintext highlighter-rouge">add n/NAME i/INGREDIENTS t/TAG s/NUMBEROFSTEPS</code>.
</li>    
<li>
<strong>Extraneous parameters</strong> for commands that do not take in parameters (such as <code class="language-plaintext highlighter-rouge">exit</code> and <code class="language-plaintext highlighter-rouge">list</code>) <strong>will be ignored</strong>.

e.g. if the command specifies <code class="language-plaintext highlighter-rouge">exit 123</code>, it will be interpreted as <code class="language-plaintext highlighter-rouge">exit</code>.
</li>
</ol>

<h2 id="viewing-help-help"> Viewing help:<code class="language-plaintext highlighter-rouge">help</code></h2>

Shows a message explaining how to access the help page.<br>
Format: `help`<br>
<!-- Examples:
```
``` -->

<h2 id="adding-a-recipe-add"> Adding a recipe: <code class="language-plaintext highlighter-rouge">add</code></h2>

Adds a recipe to the recipe manager.
**Format**: `add n/NAME i/INGREDIENTS t/TAG s/NUMBEROFSTEPS`
**Hint**: Number of steps is the number of steps in the recipe, then you will be prompted to enter the steps.
**Examples**:
```
__________________________________________________________
add n/Hotpot i/Beef, Potatoes, Carrots t/Chinese s/4

Please enter the description of step 1:
chop beef

Please enter the description of step 2:
add potatoes

Please enter the description of step 3:
add carrots

Please enter the description of step 4:
cook 5 minutes

Got it. I've added this recipe:
  [Chinese] Hotpot
Now you have 2 recipes in the list.

MaLaXiangGuo
Hotpot
__________________________________________________________
```
<p>App will prompt you to start entering the steps to the recipe will automatically count the steps and add the recipe to the recipe list.</p>
<h2 id="editing-a-recipe-step-editstep"> Editing a recipe's steps: <code class="language-plaintext highlighter-rouge">editstep</code></h2>
Edits a step for a recipe in the recipe manager.
**Format**: `editstep INDEX`, then input the step number

<h2 id="editing-a-recipe-ingredient-editingredient"> Editing a recipe's ingredients: <code class="language-plaintext highlighter-rouge">editingredient</code></h2>

Edits a recipe in the recipe manager.
Format: `edit n/NAME`
Examples:
* `edit n/Hotpot`
<p>App will print the steps to the recipe with step numbers, next input the step number you wish to edit, then re-type the whole step to replace the instruction.</p> -->

<h2 id="deleting-a-recipe-delete"> Deleting a recipe: <code class="language-plaintext highlighter-rouge">delete</code></h2>

Deletes a recipe from the recipe list.
**Format**: `delete INDEX`
**Examples**:
```
__________________________________________________________
delete 1

Noted. I've removed this recipe:
  [Chinese] MaLaXiangGuo
Now you have 1 recipes in the list.

Hotpot
__________________________________________________________
```
<p>App will remove the recipe with the corresponding index.</p>

<h2 id="finding-recipes-find"> Finding recipes: <code class="language-plaintext highlighter-rouge">find</code></h2>
Find recipes whose names contain any of the given keywords. The results will be displayed in a list, telling you the index of the recipe in the recipe list. Only the name of the recipe will be searched.

**Format**: `find KEYWORD`
**constraints**: `KEYWORD` cannot be empty.
**Examples**:
```
__________________________________________________________
find hotpot

Here are the matching items:
  [Chinese] Hotpot [Index: 2]
__________________________________________________________
```


<h2 id="viewing-a-recipe-view"> Viewing a recipe: <code class="language-plaintext highlighter-rouge">view</code></h2>

<div>Views a detailed recipe from the recipe manager if INDEX is entered.</div>

**Format**: `view INDEX`
**Hint**: If you want to view a recipe **by name**, use the **find** command first to find the index of the recipe, then use the view command to view the recipe.
**Constraints**: The index must be a positive integer larger than 0.
**Examples**:
```
__________________________________________________________
view 1
Here is the recipe you requested, which is a Chinese flavour:
name: Hotpot
__________________________________________________________
Here are 3 ingredients in the list:
1. Beef
2. Potatoes
3. Carrots
__________________________________________________________
There are 4 steps in the list
1. chop beef
2. add potatoes
3. add carrots
4. cook 5 minutes
__________________________________________________________
```

<h2 id="listing-all-recipes-list"> Listing all recipes: <code class="language-plaintext highlighter-rouge">list</code></h2>

This feature allows you to **list all recipes** you currently have. This feature is useful after using the add, edit and delete commands to make changes to the recipe list, as it shows you the updated list.
Format: `list`
Examples:
* `list`

<h2 id="clearing-all-entries-clear"> Clearing all entries: <code class="language-plaintext highlighter-rouge">clear</code></h2>

This feature allows you to **delete all recipes** that are currently stored on the recipe manager. Users may use this to clear all data when starting to use the app or when they wish to clear all data.
Format: `clear`
Examples:
* `clear`

<h2 id="exiting-the-program-exit"> Exiting the program: <code class="language-plaintext highlighter-rouge">exit</code></h2>

Exits the program.
Format: `exit`
Examples:
* `exit`

<h2 id="save-data"> Saving the data </h2>
The recipe manager data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<h2 id="edit-data"> Editing the data file </h2>

The recipe data are saved as a text file `[JAR file location]/data/[dish name].txt`. 
Here is an example of a valid file, `data/Hotpot.txt`:
```
Hotpot
Chinese
Ingredient list
Beef
Potatoes
Carrots
Step list
chop beef
add potatoes
add carrots
cook 5 minutes
```
The first line is the name of the dish. And the second line is the tag of the dish. The third line is the ingredient list. The following lines are the ingredients until the line "Step list". The following lines are the steps until the end of the file.
***warning***:
* Do **NOT** modify the save data easily.
If you change the data file **in a wrong way**, the recipe manager will not start up correctly.


<!-- <h2 id="upcoming-features"> Upcoming Features </h2>

!!! hint
    The following features are planned for the next release of the recipe manager. -->

<!-- <h2 id="using-a-recipe-use"> Using a recipe: <code class="language-plaintext highlighter-rouge">use</code></h2>
<p> 
This set of features allow you to see a step by step follow along of the steps to cook a particular dish. Once the command is triggered the command line will print the first two steps of the recipe and will keep them unchecked initially. Users may navigate from one step to the next and back using ‘w’ and ‘s’ keys. With every new step, users will be able to view the immediate upcoming step and a progress bar of how many steps are left.
</p>

Format: `use n/NAME` or `use INDEX`
Examples:
* `use n/Hotpot`
* `use 1`

<ul>
    <li>
        <h3 id="moving-to-next-step-next"> Moving to next step: <code class="language-plaintext highlighter-rouge">next</code></h3>
        <p>
            Command marks the current step as done and prints an additional step of the recipe.
        </p>
    </li>
    <li>
        <h3 id="moving-to-previous-step-prev"> Moving to previous step: <code class="language-plaintext highlighter-rouge">prev</code></h3>
        <p>
            Command unmarked the previous step from done and prints one less step of the recipe.
        </p>
    </li>
    <li>
        <h3 id="moving-to-a-specific-step-goto"> Moving to a specific step: <code class="language-plaintext highlighter-rouge">goto</code></h3>
    </li>
    <li>
        <h3 id="marking-a-step-as-done-done"> Marking a step as done: <code class="language-plaintext highlighter-rouge">done</code></h3>
    </li>
</ul> -->

<h2 id='faq'> FAQ </h2>
<strong>Q:</strong> What happens if I don't format the parameter correctly?<br>
<strong>A:</strong> If you input the correct action name, the app will prompt you for the correct input format, otherwise the app will ignore this instruction.<br>

<h2 id='command-summary'> Command Summary </h2>


|  Action   | 	Format  | Example |
|  ----  | ----  | ----  |
| **help**  | [`help`](#viewing-help-help) | |
| **add**  | [`add n/NAME i/INGREDIENTS t/TAG`](#adding-a-recipe-add)| `add n/Hotpot i/Beef, Potatoes, Carrots t/Chinese` |
| **delete**  | [`delete n/NAME`](#deleting-a-recipe-delete)| `delete INDEX` |
| **view**  | [`view INDEX`](#viewing-a-recipe-view) | `view 1`  |
| **list**  | [`list`](#listing-all-recipes-list)| |
| **clear**  | [`clear`](#clearing-all-entries-clear)| |
| **exit**  | [`exit`](#exiting-the-program-exit)| |

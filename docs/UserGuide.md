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
    * **[Editing a recipe: `edit`](#editing-a-recipe-edit)**
    * **[Deleting a recipe: `delete`](#deleting-a-recipe-delete)**
    * **[Viewing a recipe: `view`](#viewing-a-recipe-view)**
    * **[Listing all recipes: `list`](#listing-all-recipes-list)**
    * **[Clearing all entries: `clear`](#clearing-all-entries-clear)**
    * **[Exiting the program: `exit`](#exiting-the-program-exit)**
    * **[Saving the data](#save-data)**
    * **[Editing the data file](#edit-data)**
* **[Upcoming features(v2.0)](#upcoming-features)**
    * **[Using a recipe: `use`](#using-a-recipe-use)**
        * **[Moving to next step: `next`](#moving-to-next-step-next)**
        * **[Moving to previous step: `prev`](#moving-to-previous-step-prev)**
        * **[Moving to a specific step: `goto`](#moving-to-a-specific-step-goto)**
        * **[Marking a step as done: `done`](#marking-a-step-as-done-done)**
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
    
e.g. in <code class="language-plaintext highlighter-rouge">add DESCRIPTION</code>, <code class="language-plaintext highlighter-rouge">DESCRIPTION</code> is a parameter which can be used as <code class="language-plaintext highlighter-rouge">add n/NAME i/INGREDIENTS t/TAG</code>.
</li>    
<li>
<strong>Extraneous parameters</strong> for commands that do not take in parameters (such as <code class="language-plaintext highlighter-rouge">exit</code> and <code class="language-plaintext highlighter-rouge">list</code>) <strong>will be ignored</strong>.

e.g. if the command specifies <code class="language-plaintext highlighter-rouge">exit 123</code>, it will be interpreted as <code class="language-plaintext highlighter-rouge">exit</code>.
</li>
</ol>

<h2 id="viewing-help-help"> Viewing help:<code class="language-plaintext highlighter-rouge">help</code></h2>

Shows a message explaining how to access the help page.
Format: `help`

<h2 id="adding-a-recipe-add"> Adding a recipe: <code class="language-plaintext highlighter-rouge">add</code></h2>

Adds a recipe to the recipe manager.
Format: `add n/NAME i/INGREDIENTS t/TAG s/SumOfSteps`
Examples:
* `add n/Hotpot i/Beef, Potatoes, Carrots t/Chinese s/3`
<p>App will prompt you to start entering the steps to the recipe
will automatically count the steps and echo back after typing in a step
type in the step as a full sentence.</p>

<h2 id="editing-a-recipe-edit"> Editing a recipe: <code class="language-plaintext highlighter-rouge">edit</code></h2>

Edits a recipe in the recipe manager.
Format: `edit n/NAME`
Examples:
* `edit n/Hotpot`
<p>App will print the steps to the recipe with step numbers, next input the step number you wish to edit, then re-type the whole step to replace the instruction.</p>

<h2 id="deleting-a-recipe-delete"> Deleting a recipe: <code class="language-plaintext highlighter-rouge">delete</code></h2>

Deletes a recipe from the recipe manager.
Format: `delete n/NAME`
Examples:
* `delete n/Hotpot`
<p>App will prompt if you confirm to delete recipe_name, if yes then it will remove the recipe.Else revert back to main screen.</p>

<h2 id="viewing-a-recipe-view"> Viewing a recipe: <code class="language-plaintext highlighter-rouge">view</code></h2>

Views a detailed recipe from the recipe manager.
Format: `view n/NAME` or `view INDEX`
Examples:
* `view n/Hotpot`
* `view 1`

Constraints:
* The index must be a positive integer 1, 2, 3, …

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

The recipe manager data are saved as a JSON file `[JAR file location]/data/recipeData.json`. Advanced users are welcome to update data directly by editing that data file.
<div class="alert alert-warning">

!!! warning
    If you change the data file <strong>in a wrong way</strong>, the recipe manager will not start up correctly.


<h2 id="upcoming-features"> Upcoming Features </h2>

!!! hint
    The following features are planned for the next release of the recipe manager.

<h2 id="using-a-recipe-use"> Using a recipe: <code class="language-plaintext highlighter-rouge">use</code></h2>
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
</ul>

<h2 id='faq'> FAQ </h2>
<strong>Q:</strong> What happens if I don't format the parameter correctly?<br>
<strong>A:</strong> If you input the correct action name, the app will prompt you for the correct input format, otherwise the app will ignore this instruction.<br>

<h2 id='command-summary'> Command Summary </h2>


|  Action   | 	Format  | Example |
|  ----  | ----  | ----  |
| **help**  | [`help`](#viewing-help-help) | |
| **add**  | [`add n/NAME i/INGREDIENTS t/TAG`](#adding-a-recipe-add)| `add n/Hotpot i/Beef, Potatoes, Carrots t/Chinese` |
| **edit**  | [`edit n/NAME`](#editing-a-recipe-edit)| `edit n/Hotpot` |
| **delete**  | [`delete n/NAME`](#deleting-a-recipe-delete)| `delete n/Hotpot` |
| **view**  | [`view n/NAME`](#viewing-a-recipe-view) [`view INDEX`](#viewing-a-recipe-view) | `view n/Hotpot` or `view 1`  |
| **list**  | [`list`](#listing-all-recipes-list)| |
| **clear**  | [`clear`](#clearing-all-entries-clear)| |
| **exit**  | [`exit`](#exiting-the-program-exit)| |

# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding a consultation appointment: `consultation`
Adds a new consultation appointment to the appointment manager.

Format: `consultation at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME`

* The `DATE` must be in *YYYY-MM-DD* format.
* The `TIME` must be in *HH-MM* format.

Example of usage: 

* `consultation at/Cat an/Lulu on/Jon cn/91919191 cd/2023-12-12 ct/19:00`
* `consultation at/Dog an/Russ on/Sarah cn/92929292 cd/2023-10-15 ct/09:00`

### Adding a vaccination appointment: `vaccination`
Adds a new vaccination appointment to the appointment manager.

Format: `vaccination at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE cd/DATE ct/TIME`

* The `DATE` must be in *YYYY-MM-DD* format.
* The `TIME` must be in *HH-MM* format.

Example of usage:

* `vaccination at/Cat an/Lulu on/Jon cn/91919191 v/Covid vd/2023-12-12 vt/19:00`
* `vaccination at/Dog an/Russ on/Sarah cn/92929292 v/Rabies cd/2023-10-15 ct/09:00`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`

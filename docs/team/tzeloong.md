# Tan Tze Loong - Project Portfolio Page

## Overview

MyLedger is a desktop app for managing finances, designed for university students studying locally or on exchange. It is optimized for use via a Command Line Interface (CLI). For students that can type fast, MyLedger can help them record and classify their transactions into categories. Students can expect to get an overview of their transactions at a glance, which helps them to monitor their budget and expenses more effciently.

### Summary of Contributions

#### Features Implemented

1. Added the ability to edit expenditures and lend/borrow transactions

   - **What it does**: This operation allows users to edit expenditures or lend/borrow transactions one at a time. It cannot change the expenditure type of a record, only its fields

   - **Justification**: This feature improves the product significantly because a user can make mistakes when recording their daily expenditures/ transactions and the app should provide a convenient way to rectify them.

   - **Highlights**: The challenge with implementing the edit feature was differentiating the edit of normal expenditures from the edit of lend/borrow transactions because the latter required the parsing of additional fields.

2. Added the ability to find expenditures and transactions by description

   - **What it does**: This operation allows users search for specific keywords that matches the description of their expenditures or lend/borrow transactions. Keyword is case-sensitive.

   - **Justification**: By having this feature, users can find and keep track of expenditures that have similar descriptions across different categories.

   - **Highlights**: The implementation of this feature involved looping through the list of expenditures to find matching records.

#### Code Contributed

The code contributed can be found on [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=tzeloong&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

#### Enhancements to existing features

- Updated checkBudget command to allow for checking of budget in a set period of time

#### Documentation

1. User Guide

   - Designed and Added 'MyLedger' logo for application
   - Added documentation for `Adding an Academic Expenditure` feature.
   - Added documentation for `Adding a lend/borrow record` feature.
   - Added documentation for `Editing an Expenditure` feature.
   - Added documentation for `Editing a Lend/Borrow record` feature.

2. Developer Guide
   - Designed and Added 'MyLedger' logo for application
   - Added documentation and implementation for `edit Command`
   - Added documentation and implementation for `find Command`

#### Team-based tasks

1. Attended and contributed to weekly discussions.
2. Accommodated and accounted for team opinions in implementation.
3. Non-trivial bug fixes: [#80](https://github.com/AY2223S2-CS2113-T14-3/tp/issues/80),
   [#82](https://github.com/AY2223S2-CS2113-T14-3/tp/issues/82),
   [#92](https://github.com/AY2223S2-CS2113-T14-3/tp/issues/92)

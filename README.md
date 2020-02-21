# HashCode-20 : Book scanning


### Task
Given a description of libraries and books available, plan which books to scan from
which library to maximize the total score of all scanned books, taking into account that
each library needs to be signed up before it can ship books.

## Problem description

### Book
There are B di erent books with IDs from 0 to B –1. Many libraries can have a copy of
the same book, but we only need to scan each book once. Each book is described by
one parameter: the score that is awarded when the book is scanned.

### Libraries
There are L di erent libraries with IDs from 0 to L –1. Each library is described by the
following parameters:
- the set of books in the library,
- the time in days that it takes to sign the library up for scanning,
- the number of books

### Time
There are D days from day 0 to day D –1. The rst library signup can sta on day 0. D –1
is the last day during which books can be shipped to the scanning facility.

### Library signup
Each library has to go through a signup process before books from that library can be
shipped. Only one library at a time can be going through this process (because it
involves lots of planning and on-site visits at the library by logistics expe s): the signup
process for a library can sta only when no other signup processes are running. The
libraries can be signed up in any order .\
Books in a library can be scanned as soon as the signup process for that library
completes (that is, on the rst day immediately a er the signup process, see the gure
below). Books can be scanned in parallel from multiple libraries.\

For example , if
- the signup process for library 0 (that is, the library with ID 0) takes 2 days, and
- the signup process for library 1 takes 3 days, and
- library 1 is signed up before library 0 \

then
- the signup process of library 1 sta s on day 0 , and nishes on day 2 (3 days in
total)
- the rst books from library 1 can be scanned sta ing on day 3 (the next day
a er the signup process nishes)
- the signup of library 0 sta s on day 3 (the next day a er the signup process
of library 0 is done) and nishes on day 4 (2 days in total)
- the rst books from library 0 can be scanned sta ing on day 5 (the next day
a er the signup process nishes)

### Scanning
All books are scanned in the scanning facility. The entire process of sending the books,
scanning them, and returning them to the library happens in one day (note that each
library has a maximum number of books that can be scanned from this library per day).
The scanning facility is big and can scan any number of books per day.

## Input data set
### File format
Each input data set is provided in a plain text le. The le contains only ASCII
characters with lines ending with a single '\n' character (also called “UNIX- style” line
endings). When multiple numbers are given in one line, they are separated by a single
space between each two numbers. \
The rst line of the data set contains:
- an integer B ( 1 ≤ B ≤ 10 5 ) – the number of di erent books,
- an integer L ( 1 ≤ L ≤ 10 5 ) – the number of libraries,
- an integer D ( 1 ≤ D ≤ 10 5 ) – the number of days.

This is followed by one line containing B integers: S 0 , ... , S B-1 , (0 ≤ S i ≤ 10 3 ), describing
the score of individual books, from book 0 to book B –1.\
This is followed by L sections describing individual libraries from library 0 to library L –1.
Each such section contains two lines:
- the rst line, which contains:
  - N j ( 1 ≤ N j ≤ 10 5 ) – the number of books in library j ,
  - T j ( 1 ≤ T j ≤ 10 5 ) – the number of days it takes to nish the library signup
process for library j ,
  - M j ( 1 ≤ M j ≤ 10 5 ) – the number of books that can be shipped from library j
to the scanning facility per day, once the library is signed up.
- the second line, which contains N j integers, describing the IDs of the books in
the library. Each book ID is listed at most once per library. \

The total number of books in all libraries does not exceed 10 6 .

```
6 2 7       //There are 6 books, 2 libraries, and 7 days for scanning.
1 2 3 6 5 4 //The scores of the books are 1, 2, 3, 6, 5, 4 (in order).

5 2 2       //Library 0 has 5 books, the signup process takes 2 days, and the library
              can ship 2 books per day.
0 1 2 3 4   //The books in library 0 are: book 0, book 1, book 2, book 3, and book 4.

4 3 1       //Library 1 has 4 books, the signup process takes 3 days, and the library can
              ship 1 book per day.
3 2 5 0     //The books in library 1 are: book 3, book 2, book 5 and book 0.
```

## Submissions
### File format
Your submission describes which books to ship from which library and the order in
which libraries are signed up.
The submission le must sta with a line containing the integer A ( 0 ≤ A ≤ L ) – the
number of libraries to sign up (you don't need to sign up all libraries – the ones you skip
are just ignored and no books are scanned from them).
Then, the submission le must describe each library, in the order that you want them
to sta the signup process . The description of each library must contain two lines:
- the rst line containing:
  - Y ( 0 ≤ Y ≤ L – 1) – the ID of the library,
  - K ( 1 ≤ K ≤ N Y ) – the number of books to be scanned from library Y .
- the second line containing the IDs of the books to scan from that library: k 0 , ... ,
k K-1 , (0 ≤ k i ≤ B –1) in the order that they are scanned, without duplicates. \

Each library must be described only once. \
Note that:
- You don’t need to scan all books from a library you describe.
- If a library signup process nishes a er D day its description will be ignored.
- Books shipped a er D days will be ignored. \

**Note** that even though you describe the libraries one a er another in the order by
which signup processes are sta ed, the actual scanning of the books happens in
parallel for all libraries that are already signed up and still have remaining books to
scan.

#### Example
```
2           //Two libraries will be signed up for scanning.
1 3         //The rst library to do the signup process is library 1. A er the signup process it will send 3 books for scanning.
5 2 3       //Library 1 will send book 5, book 2, and book 3 in order.
0 5         //The second library to do the signup process is library 0. A er the signup process it will send 5 books.
0 1 2 3 4   // Library 0 will send book 0, book 1, book 2, book 3 and book 4 in that order.
```

### Scoring
Your score is the sum of the scores of all books that are scanned within D days. Note
that if the same book is shipped from multiple libraries (as books 2 and 3 are in the 
figure below), the solution will be accepted but the score for the book will be awarded
only once.



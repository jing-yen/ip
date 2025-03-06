# Voyager User Guide

Your personal assistant in managing tasks for space exploration.

![Voyager Screenshot](voyager_screenshot.png)

Voyager is a command-line task management application designed to help you keep track of your tasks efficiently. Whether you are planning missions, managing resources, or scheduling events, Voyager is here to assist you.

## Getting Started

To start using Voyager, simply run the `Voyager.jar` file. Once launched, Voyager will greet you and await your commands.

## Basic Commands

Here are some basic commands to get you started:

### 1. Listing Tasks

**Command:** `list`

**Description:** Displays all tasks currently stored in Voyager's memory.

**Example:**
```
list
```

**Expected Output:**
```
Accessing my Digital Tape Recorder (DTR)...
1. [X] todo read user guide
2. [/] deadline submit mission report /by 2024-08-20
3. [X] event team meeting /from 2024-08-25 /to 2024-08-25
```

### 2. Adding a Todo Task

**Command:** `todo <description>`

**Description:** Adds a new todo task with the given description.

**Example:**
```
todo read user guide
```

**Expected Output:**
```
Roger. Ground control requests for Todo:
  1. [X] read user guide
My memory bank is 1/100 full.
```

### 3. Adding a Deadline Task

**Command:** `deadline <description> /by <date/time>`

**Description:** Adds a new deadline task with the given description and deadline date/time. Date/time should be in YYYY-MM-DD format.

**Example:**
```
deadline submit mission report /by 2024-08-20
```

**Expected Output:**
```
Roger. Ground control requests for Deadline:
  2. [ ] submit mission report (by: 2024-08-20)
My memory bank is 2/100 full.
```

### 4. Adding an Event Task

**Command:** `event <description> /from <start date/time> /to <end date/time>`

**Description:** Adds a new event task with the given description, start date/time, and end date/time. Date/time should be in YYYY-MM-DD format.

**Example:**
```
event team meeting /from 2024-08-25 /to 2024-08-25
```

**Expected Output:**
```
Roger. Ground control requests for Event:
  3. [ ] team meeting (from: 2024-08-25 to: 2024-08-25)
My memory bank is 3/100 full.
```

### 5. Marking a Task as Done

**Command:** `mark <task index>`

**Description:** Marks the task at the given index as done. The index is the number shown in the `list` command output.

**Example:**
```
mark 1
```

**Expected Output:**
```
Beep-boop. Marked task 1 as done.
```

### 6. Unmarking a Task as Not Done

**Command:** `unmark <task index>`

**Description:** Marks the task at the given index as not done.

**Example:**
```
unmark 1
```

**Expected Output:**
```
Beep-boop. Unmarked task 1 as not done.
```

### 7. Deleting a Task

**Command:** `delete <task index>`

**Description:** Deletes the task at the given index.

**Example:**
```
delete 1
```

**Expected Output:**
```
You have purged item 1 from my memory bank.
My memory bank is 2/100 full.
```

### 8. Finding Tasks

**Command:** `find <keyword>`

**Description:** Finds tasks that contain the given keyword in their description.

**Example:**
```
find report
```

**Expected Output:**
```
Here are the matching tasks in your list:
2. [/] deadline submit mission report /by 2024-08-20
```

### 9. Exiting Voyager

**Command:** `bye`

**Description:** Terminates the Voyager application.

**Example:**
```
bye
```

**Expected Output:**
```
Bye. Hope not to see you in the cold, dark outer space!
```

## Error Handling

Voyager is designed to handle various user inputs and errors gracefully. If you encounter an issue, Voyager will provide informative messages to guide you. For example, entering an invalid command will result in an error message indicating that the command is not recognized.

## Contributing

// Add contribution guidelines if applicable.

## License

// Add license information if applicable.

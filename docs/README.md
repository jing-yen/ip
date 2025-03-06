# Voyager User Guide

Voyager is a command-line task management application designed for space exploration missions (but suitable for everything else!).  It helps you track tasks, deadlines, and events, keeping your mission on schedule.  Voyager is optimized for a Command Line Interface (CLI), providing efficiency for users comfortable with typing commands.

## Quick Start

1.  **Prerequisites:** Ensure you have Java installed on your system.  (Preferably Java 17).
2.  **Download:** Obtain the `Voyager.jar` file from the 'Releases' tab.
3.  **Run:**
    *   Open a command terminal (or command prompt).
    *   Navigate to the directory where you saved `Voyager.jar` using the `cd` command (e.g., `cd Downloads`).
    *   Execute Voyager using the command: `java -jar Voyager.jar`
4.  **Interface:**  Voyager operates entirely within the command-line interface.  You'll see a prompt where you can enter commands.

## Features

:information_source: **Notes about the command format:**

*   Words in `UPPER_CASE` represent parameters you need to provide.
*   Items in `[square brackets]` are optional.
*    `<angled brackets>` show the expected type or format of data
*   The `...` symbol means you can repeat the preceding element multiple times.

### Listing Tasks: `list`

Displays all tasks currently stored in Voyager's memory.

*   **Format:** `list`

*   **Example Output:**

    ```
    Accessing my Digital Tape Recorder (DTR)...
    00000000. [T][/]: { desc: todo read user guide }
    00000001. [T][X]: { desc: go to school }
    00000010. [D][X]: { desc: submit mission report, by: today }
    ```

### Adding a Todo Task: `todo`

Adds a new todo task.

*   **Format:** `todo DESCRIPTION`

*   **Example:** `todo Prepare launch sequence documentation`

*   **Example Output:**

    ```
    Roger. Ground control requests for Todo:
      00000000. [T][/]: { desc: Prepare launch sequence documentation }
    My memory bank is 1/100 full.
    ```

### Adding a Deadline Task: `deadline`

Adds a task with a deadline.

*   **Format:** `deadline DESCRIPTION /by DEADLINE`

*   **Example:** `deadline Submit fuel consumption report /by 2025-10-31`

*   **Example Output:**

    ```
    Roger. Ground control requests for Deadline:
      00000001. [D][/]: { desc: Submit fuel consumption report, by: 2025-10-31 }
    My memory bank is 2/100 full.
    ```

### Adding an Event Task: `event`

Adds an event with a start and end date/time.

*   **Format:** `event DESCRIPTION /from START /to END`

*   **Example:** `event Solar panel alignment check /from 2025-11-15 /to 2025-11-15`

*   **Example Output:**

    ```
    Roger. Ground control requests for Event:
      00000010. [E][/]: { desc: Solar panel alignment check, from: 2024-11-15, to: 2024-11-15 }
    My memory bank is 3/100 full.
    ```

### Marking a Task as Done: `mark`

Marks a task as completed.

*   **Format:** `mark TASK_INDEX`

*   **Example:** `mark 2`

*   **Example Output:**

    ```
    Beep-boop. Marked task 2 as done.
    ```

### Unmarking a Task as Not Done: `unmark`

Reverts a completed task to an incomplete state.

*   **Format:** `unmark TASK_INDEX`

*   **Example:** `unmark 2`

*   **Example Output:**

    ```
    Beep-boop. Unmarked task 2 as not done.
    ```

### Deleting a Task: `delete`

Removes a task from Voyager's memory.

*   **Format:** `delete TASK_INDEX`

*   **Example:** `delete 1`

*   **Example Output:**

    ```
    You have purged item 1 from my memory bank.
    My memory bank is 2/100 full.
    ```

### Finding Tasks: `find`

Searches for tasks containing a specific keyword.

*   **Format:** `find KEYWORD`

*   **Example:** `find navigation`
*   **Example Output:**

    ```
    Here are the matching tasks in your list:
      00000001. [D][/]: { desc: submit mission report, by: today }
    ```

### Exiting Voyager: `bye`

Terminates the Voyager application.

*   **Format:** `bye`

*   **Example Output:**

    ```
    Bye. Hope not to see you in the cold, dark outer space!
    ```

## Error Handling

Voyager provides informative messages for invalid commands or errors.  For example, if you enter an unrecognized command, you will receive an error message indicating the command is not valid.

## Command Summary

| Action                | Format                                        |
|-----------------------|-----------------------------------------------|
| List Tasks            | `list`                                        |
| Add Todo              | `todo DESCRIPTION`                            |
| Add Deadline          | `deadline DESCRIPTION /by DEADLINE`           |
| Add Event             | `event DESCRIPTION /from START /to END`        |
| Mark Task as Done     | `mark TASK_INDEX`                             |
| Unmark Task as Not Done | `unmark TASK_INDEX`                          |
| Delete Task           | `delete TASK_INDEX`                           |
| Find Tasks            | `find KEYWORD`                                |
| Exit Voyager          | `bye`                                         |
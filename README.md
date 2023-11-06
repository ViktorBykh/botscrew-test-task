# <h1 align="center"> <img src="https://em-content.zobj.net/source/apple/354/graduation-cap_1f393.png" width="35"/> University Console Interface <img src="https://em-content.zobj.net/source/apple/354/graduation-cap_1f393.png" width="35"/> </h1>

This is a simple Spring Boot Java project with a console interface for managing a university. The application handles departments, lectors, and provides various commands to retrieve information from the relational database.

## Commands
- You can see greeting message for user with `Start` command:
  - **User Input:** `Start`
  - **Answer:** `Welcome to the University Console Interface! Enter a command (type 'exit' to quit)`
- You can see all `commands: descriptions` with `help` command.
   - **User Input:** `help`
   - **Answer:** `<..>`
     <br>`Show statistics for: Department statistics`
     <br>`Start: Start the console interface`
     <br>`Show the average salary for the department: The average salary for the department`
     <br>`Who is head of department: Find the head of a department`
     <br>`Show count of employee for: Employee count for a department`
     <br>`Global search by: Global search by template`

1. **Who is head of department {department_name}**
    - **User Input:** `Who is head of department {department_name}`
    - **Answer:** `Head of {department_name} department is {head_of_department_name}`

2. **Show statistics for {department_name}**
    - **User Input:** `Show {department_name} statistics`
    - **Answer:** `{department_name} department statistics:`
        - `assistants - {assistants_count}`
        - `associate professors - {associate_professors_count}`
        - `professors - {professors_count}`

3. **Show the average salary for the department {department_name}**
    - **User Input:** `Show the average salary for the department {department_name}`
    - **Answer:** `The average salary of {department_name} is {average_salary}`

4. **Show count of employee for {department_name}**
    - **User Input:** `Show count of employee for {department_name}`
    - **Answer:** `Employee count for History department:  {employee_count}`

5. **Global search by {template}**
    - **User Input:** `Global search by {template}`
    - **Example:** `Global search by van`
    - **Answer:** `Ivan Petrenko, Petro Ivanov`
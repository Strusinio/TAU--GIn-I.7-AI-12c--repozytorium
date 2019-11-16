Feature: Check if Football Club was added?
  We want to know if adding football clubs works.

  Scenario Outline: Football Club added correctly
    Given Football Club ID <id> FootballClub Name <name>
    And Football Club Stadium Capacity <stadiumCapacity>
    And Football Club Location <location>
    And Football Club Ground <ground>
    And Football Club League <league>
    When Football Club is added
    Then it will exist with a given id <id> in the database

    Examples:
    | id | name                | stadiumCapacity | location      | ground                          | league    |
    | 1  | 'FC Barcelona'      | 95000           | 'Barcelona'   | 'Camp Nou'                      | 'La Liga' |
    | 2  | 'Real Madrid'       | 80000           | 'Madrid'      | 'Estadio Bernabeu'              | 'La Liga' |
    | 3  | 'Sevilla FC'        | 50000           | 'Sevilla'     | 'Estadio Ramón Sánchez Pizjuán' | 'La Liga' |
    | 4  | 'Atletico Madrid'   | 75000           | 'Madrid'      | 'Wanda Metropolitano'           | 'La Liga' |
    | 5  | 'Valencia CF'       | 60000           | 'Valencia'    | 'Estadio Mestalla'              | 'La Liga' |

  Scenario Outline: Try to count how many leagues starts contains word "La"
    Given Football Clubs in base
    When finding out how many leagues start with the word La<regex>
    Then I am counting it <number> which leagues contains La"
    Examples:
    | number | regex  |
    | 5      | 'La.*'|

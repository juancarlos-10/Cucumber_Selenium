@functional
Feature: Categoria Comics
  Como usuario quiero leer chistes de tester para reirme y pasar un buen rato.

  @browser @slow @test
  Scenario: Ingresar a la categoria comics desde la pantalla Home
    Given El usuario se encuentra en la pagia Home de imalittletester
    When Hace click sobre el boton The Little Tester Comics
    Then Se debe redirigir a la pantalla Comics



  @outline @fast
  Scenario Outline: Bebiendo
    Given Hay <inicio> cervezas
    When Tomo <cantidad>  cervezas
    Then Deberian quedar <resto> cervezas

    @test1
    Examples:
      | inicio  | cantidad  | resto |
      | 10      | 5         | 5     |
      | 10      | 10        | 0     |

  @test2
    Examples:
      | inicio  | cantidad  | resto |
      | 80      | 50        | 30    |
      | 90      | 10        | 80    |


  @docstring @fast
  Scenario: Docs string
      Given  Un blog llamado "Random" con el siguiente conternido
      """
      Teoria de la tierra en forma de rosquilla
      =======================
      Este seria el primer parrafo
      asasasasasasa
      """



  @datatable
  Scenario: Data table
        Given  Los siguieetes usuarios existentes;
        | nombre  | email |twiter|
        | juan    | juan@gmail.com | @juan  |
        | orion   | ori@gmail.com  | @ori   |




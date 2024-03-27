@funcional
  Feature: MongoDB
    Como QA Automation Engenier quiero acceder a la base de datos Mongo para verificar el Backend de la aplicacion


    Scenario Outline: Conectarse a la base de datos mipcgamer y verificar los componentes
      Given Me he conectado a la base de datos mipcgamer
      When  Obtengo la PC de QA Automation
      Then  Debo obtener los siguientes componentes "<mother>" "<cpu>" "<memory1>" "<memory2>"

      Examples:
        |mother                     | cpu                  | memory1              |  memory2              |
        |Gigabyte Aorus elite bise  |Ryzen 5 3600 4.2 GHZ  |HyperX Fury 00R4 8GB  |HyperX Fury 00R4 8GB   |
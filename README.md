# Programming Fundamentals' Second Semester Project (Course 2022/23)
Author/a: Óscar Alfonso Mitillo López-Barajas   UVUS:MFC0613

## *Folder's structure*

* **/src**: Directory with the source code.
  * **fp.types**: Package containing project types.
  * **fp.test**: Package containing the test class of the project.
  * **fp.utils**:  Package containing the utility classes (checkers). 
* **/data**: Contains the project dataset.
    * **WorldCupMatches.csv**: .csv file containing data from all the World Cup matches of the history until 2014.
    
## *Dataset's structure*

Originally, the dataset had 20 columns but it was expanded by adding one more column containing the minute and second, expressed as a Double value, of the first shot which was randomly generated. Each row contains information about one Football World Cup match until the World Cup of 2014 played in Brasil. This 21 columns are now explained:

* **year**: of type Integer,  indicates the year.
* **dateTime**: of type LocalDateTime, indicates the date and time.
* **stage**: of type String, indicates the stage of the tournament (Final, Semi-finals. quarter-finals...).
* **stadium**: of type String, indicates the name of the stadium.
* **city**: of type String, indicates the name of the city.
* **homeTeamName**: of type String, indicates the name of the team playing as local.
* **homeTeamGoals**: of type Integer, indicates the local team number of goals. 
* **awayTeamName**: of type String, indicates the name of the team playing as visitant.
* **awayTeamGoals**: of type Integer, indicates the visitant team number of goals.
* **winConditions**: of type String, description of the victory (it does not take any value in any match).
* **attendance**: of type Integer,  indicates the number of spectators.
* **halfTimeHomeGoals**: of type Integer,  indicates the local team number of goals before the break.
* **halfTimeAwayGoals**: of type Integer,  indicates the visitant team number of goals before the break.
* **referee**: of type String,  indicates the name of the main referee.
* **assistant1**: of type String,  indicates the name of the second referee.
* **assistant2**: of type String,  indicates the name of the third referee.
* **roundId**: of type Integer,  indicates the round identification.
* **matchId**: of type Integer,  indicates the match identification.
* **homeTeamInitials**: of type String, indicates the initials of the team playing as local.
* **awayTeamInitials**: of type String, indicates the initials of the team playing as visitant.
* **firstShotMinute**: of type Double, indicates the minute when the first shot was taken.

## Implemented types

The implemented types in the project are the following:

### Base Type - Match
Represents one Football World Cup match.

**Propperties**:

- _year_, of type _Integer_, public. Indicates the year.
- _dateTime_, of type _LocalDateTime_, public. Indicates the date and time.
- _stage_, of type _String_, public. Indicates the stage of the tournament (Final, Semi-finals. quarter-finals...).
- _location_, of type _Location_, public. object type that contains the stadium and city.
- _homeTeamName_, of type _String_, public. Indicates the name of the team playing as local.
- _homeTeamGoals_, of type _Integer_, public. Indicates the local team number of goals. 
- _awayTeamName_, of type _String_, public. Indicates the name of the team playing as visitant.
- _awayTeamGoals_, of type _Integer_, public. Indicates the visitant team number of goals.
- _winConditions_, of type _String_, public. Description of the victory (it is always " ").
- _attendance_, of type _Integer_, public. Indicates the number of spectators.
- _halfTimeHomeGoals_, of type _Integer_, public. Indicates the local team number of goals before the break.
- _halfTimeAwayGoals_, of type _Integer_, public. Indicates the visitant team number of goals before the break.
- _referees_, of type _List\<String\>_, public. Contains the name of the 3 referees.
- _roundId_, of type _Integer_, public. Indicates the round identification.
- _*matchId_, of type _Integer_, public. Indicates the match identification.
- _homeTeamInitials_, of type _String_, public. Indicates the initials of the team playing as local.
- _awayTeamInitials_, of type _String_, public. Indicates the initials of the team playing as visitant.
- _firstShotMinute_, of type _Double_, public. Indicates the minute when the first shot was taken.

**Derived properties**:

- _result_, of type _Result_, public. It can take LOCAL_WINS (local goals > visitant goals), VISITANT_WINS (local goals < visitant goals) or DRAW (local goals = visitant goals) as values.
- _recent_, of type _String_, public. Indicates the name of the third referee.
- _totalGoals_, of type _String_, public. Indicates the name of the third referee.
- _winner_, of type _String_, public. Indicates the name of the third referee.

**Constructores**: 

- C1: Tiene un parámetro por cada propiedad básica del tipo.
- C2: Crea un objeto de tipo ```Partida``` a partir de los siguientes parámetros: ```Boolean clasificatoria, TipoVictoria tipoVictoria, Resultado resultado, String jugadorBlancas, String jugadorNegras, Integer ratingBlancas, Integer ratingNegras, String movimientos, String apertura, LocalDate fecha, Integer duracion```.

**Restricciones**:
 
- R1: La duración debe estar entre 1 y 60.
- R2: El movimiento inicial debe ser uno de los movimientos siguientes: h3, h4, g3, g4, f3, f4, e3, e4, d3, d4, c3, c4, b3, b4, a3, a4, Nh3, Nf3, Nc3, Na3.
- R3: El rating de las blancas debe ser mayor que cero.
- R4: El rating de las negras debe ser mayor que cero.
***Criterio de igualdad**: Dos partidas son iguales si todas sus propiedades básicas son iguales.

**Criterio de ordenación**: Por fecha, duración y número de movimientos.

**Otras operaciones**:

- _String getMovimiento(Integer numMovimiento)_: Devuelve el movimiento dado por el número numMovimiento. Eleva ```IllegalArgumentException``` si ```numMovimiento``` no está en el intervalo [1, getNumMovimientos()]

#### Tipos auxiliares

- TipoVictoria, enumerado. Puede tomar los valores OUTOFTIME, RESIGN, MATE, DRAW.
- Resultado, enumerado. Puede tomar los valores WHITE, BLACK, DRAW.

### Factoría - FactoriaPartidas
Clase de factoría para construir objetos de tipo Partidas.

- _Partidas leerPartidas(String nomfich)_:Crea un objeto de tipo Partidas a partir de la información recogida en el archivo csv, cuya ruta se da como parámetro.


### Tipo Contenedor - Partidas

Clase contenedora de los objetos de tipo Partida.

**Propiedades**:

-  _partidas_, de tipo _List\<Partida\>_, consultable. Lista de partidas de ajedrez 
-  _numero partidas_, de tipo _Integer_, consultable. Número de partidas del contenedor. 
 
**Constructores**: 

- C1: Constructor por defecto. Creal un objeto de tipo Partidas sin ninguna partida almacenada.
- C2: Constructor con un parámetro de tipo Collection\<Partida\>. Crea un objeto de tipo Partidas con las partidas incluidas en la colección dada como parámetro.
- C3: Constructor con un parámetro de tipo Stream\<Partida\>. Crea un objeto de tipo Partidas con las partidas incluidas en el Stream dado 

**Criterio de igualdad**: Dos partidas son iguales si lo son sus propiedades partidas.


**Otras operaciones**:
- _void agregarPartida(Partida p)_: Añade una partida de ajedrez al objeto.
- _Double getPromedioDuracionesMedias(TipoVictoria vic)_: Devuelve la media de la duración media(en segundos) por turno de las partidas. Si la media no se puede calcular, devuelve cero.
- _Map<String, Double> getPorcentajesSiguienteMovimiento(String movimiento, Integer numeroMovimiento)_: Devuelve un Map en el que las claves son movimientos siguientes al dado como parámetro (según el movimiento y la posición en la que se hace), y los valores el porcentaje de veces que se ha hecho ese movimiento. Por ejemplo,     si el movimiento es "Nc6" y el número de movimiento es el 6, el Map contiene como claves los movimientos hechos en séptimo lugar tras un movimiento "Nc6". Los valores serán el porcentaje de veces que se han hecho esos movimientos. Eleva ```IllegalArgumentException```si numeroMovimiento no es mayor o igual que uno.
- _Double getPorcentajeVictoriasDeApertura(String apertura, Resultado resultado)_: Devuelve el porcentaje de partidas que incluyen la cadena de apertura en su apertura y cuyo resultado es el dado como parámetro.
- _SortedSet<Partida> getNPartidasMasCortas(Integer anyo, Integer n)_: Devuelve un conjunto ordenado con las n partidas más cortas jugadas en el año dado como parámetro. El conjunto estará ordenado por el número de movimientos de la partida.
- _List<String> getNMejoresJugadores(Integer anyo, Integer n)_: Devuelve una lista con los ids de los n jugadores con más victorias en el año dado como parámetro.
- _Long getTiempoTotalJuego(String idJugador)_: Devuelve el total de minutos jugados por el jugador dado como parámetro.
- _String getJugadorMasVictorias(Integer anyo, Resultado resultado)_:
Devuelve true si hay algún jugador que tenga más de n victorias.
- _Map<TipoVictoria, String> getGanadorNPorTipoVictoria(Integer n)_:
Devuelve un map en el que las claves son los tipos de victoria y el valor es el enésimo jugador con más rating entre los jugadores que han tenido victorias de ese tipo. Es decir, si hacemos un ranking de los jugadores según su rating, nos quedaríamos con el que está en la posición n.



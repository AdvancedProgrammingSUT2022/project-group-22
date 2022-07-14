// package civilization.views;

// import civilization.controllers.*;
// import civilization.enums.*;
// import java.util.*;
// import java.util.regex.*;

// public class GameView extends Processor {
// private static GameView instance = null;
// GameController gameController = GameController.getInstance();
// UnitController unitController = UnitController.getInstance();
// MapController mapController = MapController.getInstance();
// Matcher matcher;

// public static GameView getInstance() {
// instance = instance != null ? instance : new GameView();
// return instance;
// }

// public String run() {
// String command;
// while (scanner.hasNext()) {
// command = getInput();
// if ((matcher = getMatcher(command, Command.MENUEXIT)) != null)
// return "gameMenu";
// else if ((matcher = getMatcher(command, Command.NEXTTURN)) != null)
// unitController.nextTurn();
// else if ((matcher = getMatcher(command, Command.INFOCITY)) != null)
// gameController.citiesInfo();
// else if ((matcher = getMatcher(command, Command.INFORESEARCH)) != null)
// gameController.researchInfo();
// else if ((matcher = getMatcher(command, Command.INFODEALS)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.INFODEMOGRAPHICS)) != null)
// gameController.demographicInfo();
// else if ((matcher = getMatcher(command, Command.INFODIPLOMACY)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.INFODIPLOMATIC)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.INFOECONOMIC)) != null)
// gameController.economicInfo();
// else if ((matcher = getMatcher(command, Command.INFOMILITARY)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.INFONOTIFICATIONS)) != null)
// gameController.notificationInfo();
// else if ((matcher = getMatcher(command, Command.INFOUNIT)) != null)
// gameController.unitInfo();
// else if ((matcher = getMatcher(command, Command.INFOVICTORY)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.SELECTCITYNAME)) != null)
// gameController.selectCityByName(matcher);
// else if ((matcher = getMatcher(command, Command.SELECTCITYPOSITION)) != null)
// gameController.selectCityByPosition(matcher);
// else if ((matcher = getMatcher(command, Command.SELECTUNITCOMBAT)) != null)
// gameController.selectCombatUnit(matcher);
// else if ((matcher = getMatcher(command, Command.SELECTUNITNONCOMBAT)) !=
// null)
// gameController.selectNonCombatUnit(matcher);
// else if ((matcher = getMatcher(command, Command.BUYUNIT)) != null)
// unitController.buyUnit(matcher);
// else if ((matcher = getMatcher(command, Command.ATTACK)) != null)
// unitController.attack(matcher);
// else if ((matcher = getMatcher(command, Command.MOVETO)) != null)
// unitController.move(matcher);
// else if ((matcher = getMatcher(command, Command.SLEEP)) != null)
// unitController.sleep();
// else if ((matcher = getMatcher(command, Command.WAKE)) != null)
// unitController.wake();
// else if ((matcher = getMatcher(command, Command.ALERT)) != null)
// unitController.alert();
// else if ((matcher = getMatcher(command, Command.FORTIFY)) != null)
// unitController.fortify();
// else if ((matcher = getMatcher(command, Command.FORTIFYHEAL)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.GARRISON)) != null)
// unitController.garrison();
// else if ((matcher = getMatcher(command, Command.SETUP)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.FOUND)) != null)
// unitController.foundCity(matcher);
// else if ((matcher = getMatcher(command, Command.CANCEL)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.DELETE)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.BUILDROAD)) != null)
// unitController.buildRoad();
// else if ((matcher = getMatcher(command, Command.BUILDRAILROAD)) != null)
// unitController.buildRailRoad();
// else if ((matcher = getMatcher(command, Command.BUILDFARM)) != null)
// unitController.buildImprovements(Improvement.FARM);
// else if ((matcher = getMatcher(command, Command.BUILDMINE)) != null)
// unitController.buildImprovements(Improvement.MINE);
// else if ((matcher = getMatcher(command, Command.BUILDTRADINGPOST)) != null)
// unitController.buildImprovements(Improvement.TRADINGPOST);
// else if ((matcher = getMatcher(command, Command.BUILDLUMBERMILL)) != null)
// unitController.buildImprovements(Improvement.LUMBERMILL);
// else if ((matcher = getMatcher(command, Command.BUILDPASTURE)) != null)
// unitController.buildImprovements(Improvement.PASTURE);
// else if ((matcher = getMatcher(command, Command.BUILDCAMP)) != null)
// unitController.buildImprovements(Improvement.CAMP);
// else if ((matcher = getMatcher(command, Command.BUILDPLANTATION)) != null)
// unitController.buildImprovements(Improvement.PLANTATION);
// else if ((matcher = getMatcher(command, Command.BUILDQUARRY)) != null)
// unitController.buildImprovements(Improvement.QUARRY);
// else if ((matcher = getMatcher(command, Command.REMOVEFEATURE)) != null)
// unitController.removeFeature(matcher);
// else if ((matcher = getMatcher(command, Command.REMOVEROUTE)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.REPAIR)) != null)
// return null;
// else if ((matcher = getMatcher(command, Command.PRINTAREA)) != null)
// mapController.printAreaCheck(matcher);
// else if ((matcher = getMatcher(command, Command.PRINTCITY)) != null)
// mapController.printCityCheck(matcher);
// else if ((matcher = getMatcher(command, Command.PRINTTILE)) != null)
// mapController.printTileCheck(matcher, Command.PRINTTILE);
// else if ((matcher = getMatcher(command, Command.PRINTUNITPOSITION)) != null)
// mapController.printTileCheck(matcher, Command.PRINTUNITPOSITION);
// else if ((matcher = getMatcher(command, Command.MAPMOVED)) != null)
// mapController.moveMap(Command.MAPMOVED);
// else if ((matcher = getMatcher(command, Command.MAPMOVEL)) != null)
// mapController.moveMap(Command.MAPMOVEL);
// else if ((matcher = getMatcher(command, Command.MAPMOVER)) != null)
// mapController.moveMap(Command.MAPMOVER);
// else if ((matcher = getMatcher(command, Command.MAPMOVEU)) != null)
// mapController.moveMap(Command.MAPMOVEU);
// else if ((matcher = getMatcher(command, Command.INCREASETURN)) != null)
// unitController.skipTurns(matcher);
// else if ((matcher = getMatcher(command, Command.INCREASEGOLD)) != null)
// gameController.addGold(matcher);
// else if ((matcher = getMatcher(command, Command.INCREASEBEAKERS)) != null)
// gameController.addBeakers(matcher);
// else if ((matcher = getMatcher(command, Command.INSTANTBUILD)) != null)
// unitController.instantBuild();
// else if ((matcher = getMatcher(command, Command.INSTANTRESEARCH)) != null)
// unitController.instantResearch();
// else if ((matcher = getMatcher(command, Command.BUYTILE)) != null)
// gameController.buyTile(matcher);
// else if ((matcher = getMatcher(command, Command.RESEARCH)) != null)
// gameController.addResearch(matcher);
// else if ((matcher = getMatcher(command, Command.ADDRESOURCE)) != null)
// gameController.addResource(matcher);
// else
// System.out.println("invalid Command!");
// }
// return "gameMenu";
// }

// // messages
// public void currentPlayer(String username) {
// System.out.println("current player: " + username);
// }

// public void unitSelected(String type, int[] coordinates) {
// System.out.println(type + " unit on tile " + coordinates[0] + ":" +
// coordinates[1] + " has been selected");
// }

// public void citySelected(String name) {
// System.out.println(name + " city has been selected");
// }

// public void cityFounded(String name, String username, int i, int j) {
// System.out.println(name + " city founded for player " + username + " on tile
// " + i + ":" + j);
// }

// public void unitBought(String type) {
// System.out.println(type + " unit bought successfully");
// }

// public void unitSlept() {
// System.out.println("the selected unit has been put to sleep");
// }

// public void unitAlerted() {
// System.out.println("successfully change status of the selected unit");
// }

// public void unitFortified() {
// System.out.println("Selected unit successfully fortified");
// }

// public void unitWoke() {
// System.out.println("you can use the selected unit from now on");
// }

// public void unitGarrisoned() {
// System.out.println("selected unit attached to the tile");
// }

// public void taskCompleted(String task, int[] coordinates) {
// String str = task + "was built on tile " + coordinates[0] + ":" +
// coordinates[1];
// gameController.addMessage(str);
// System.out.println(str);
// }

// public void moveCompleted(String name, int[] coordinates) {
// String str = name + " has moved to tile " + coordinates[0] + ":" +
// coordinates[1];
// gameController.addMessage(str);
// System.out.println(str);
// }

// public void researchAdded() {
// System.out.println("research added successfully");
// }

// public void technologyAdded(String tech) {
// System.out.println(tech + " technology added successfully");
// }

// public void goldIncreased(int amount) {
// System.out.println("gold increased by " + amount);
// }

// public void beakersIncreased(int amount) {
// System.out.println("beakers increased by " + amount);
// }

// public void turnsIncreased(int amount) {
// System.out.println(amount + " turns skipped");
// }

// // errors
// public void cityInaccessible() {
// System.out.println("you do not have access to this city");
// }

// public void noUnitSelected() {
// System.out.println("no unit has been selected");
// }

// public void noCitySelected() {
// System.out.println("no city has been selected");
// }

// public void invalidTile() {
// System.out.println("no tile with these coordinates exists");
// }

// public void onTarget() {
// System.out.println("you are already on your selected tile");
// }

// public void tileOccupied() {
// System.out.println("a similar unit occupies this tile");
// }

// public void tileInaccessible() {
// System.out.println("you can't move to this tile");
// }

// public void mpLow() {
// System.out.println("insufficient mp");
// // System.out.println(
// // "you don't have enough mp to directly move to this tile, the unit will
// start
// // nearing the target");
// }

// public void unitNotSettler() {
// System.out.println("the selected unit is not a settler unit");
// }

// public void unitNotWorker() {
// System.out.println("the selected unit is not a worker unit");
// }

// public void unitNotOnTile() {
// System.out.println("the unit is not on the selected tile");
// }

// public void invalidMilitaryUnit() {
// System.out.println("there is no military unit on the selected tile");
// }

// public void invalidCivilianUnit() {
// System.out.println("there is no civilian unit on the selected tile");
// }

// public void unitInaccessible() {
// System.out.println("this unit belongs to another civilization");
// }

// public void tileHasRoad(int[] coordinates) {
// System.out.println("tile " + coordinates[0] + ":" + coordinates[1] + "
// already has a road");
// }

// public void tileNotYours() {
// System.out.println("this tile belongs to another civilization");
// }

// public void tileOwned() {
// System.out.println("you already own this tile");
// }

// public void noCityNearby() {
// System.out.println("this tile isn't neighboring your current civilization");
// }

// public void negativeHappiness() {
// System.out.println("the level of happiness is negative");
// }

// public void dontHaveCity() {
// System.out.println("you should own at least one city");
// }

// public void technologyInaccessible() {
// System.out.println("this technology isn't accessible");
// }

// public void hadTechnology() {
// System.out.println("you already have this technology");
// }

// public void invalidCity() {
// System.out.println("no city with this name exists");
// }

// public void attackImpossible() {
// System.out.println("attack is not Possible");
// }

// public void turnNotOver() {
// System.out.println("you have units with unassigned tasks");
// }

// public void goldLow() {
// System.out.println("insufficient gold");
// }

// public void tileHasImprovement() {
// System.out.println("this tile already has an improvement");
// }

// public void noFeature(Feature feature) {
// System.out.println("this tile does not have the " +
// feature.name().toLowerCase() + "feature");
// }

// public void invalidLocation() {
// System.out.println("you can't build this improvement here");
// }

// public void insufficientTechnologies() {
// System.out.println("you do not have the prerequired technologies");
// }

// public void insufficientResources() {
// System.out.println("you do not have the prerequired resources");
// }

// public void noSuchUnitType(String type) {
// System.out.println(type + " is not an available unit type");
// }

// public void noSuchTechnology(String tech) {
// System.out.println(tech + " is not an available technology");
// }

// public void featureIrremovable(String feature) {
// System.out.println(feature + " is not a removable feature");
// }

// public void nameTaken(String name) {
// System.out.println("a city named \"" + name + " already exists");
// }

// public void cityExists(int[] coordinates) {
// System.out.println("tile " + coordinates[0] + ":" + coordinates[1] + " is a
// city center");
// }

// public void tooCloseToCity(int[] coordinates) {
// System.out.println("tile " + coordinates[0] + ":" + coordinates[1] + " is too
// close to another city center");
// }

// public void pathBlocked(String name, int[] pos, int[] dest) {
// System.out.println(name + " on tile " + pos[0] + ":" + pos[1]
// + " can no longer move towards tile " + dest[0] + ":" + dest[1]);
// }

// public void noBuildTask() {
// System.out.println("the selected unit doesn't have any build task");
// }

// // print info
// public void printResearchInfo(String nickName, HashMap<Technology, Integer>
// currentResearch,
// ArrayList<Technology> possibles, ArrayList<Technology> done) {
// System.out.println(Color.WHITE.getColor() + "User nickname: " +
// Color.RESET.getColor() + nickName);
// System.out.println(Color.WHITE.getColor() + "Current researches:" +
// Color.RESET.getColor());
// for (Technology technology : currentResearch.keySet()) {
// String key = technology.name();
// Integer value = currentResearch.get(technology);
// System.out.println(key + " " + value);
// }
// System.out.println(Color.WHITE.getColor() + "Possible technologies to
// research:" + Color.RESET.getColor());
// for (Technology possible : possibles) {
// System.out.println(possible.name());
// }
// System.out.println(Color.WHITE.getColor() + "Acquired technologies:" +
// Color.RESET.getColor());
// for (Technology technology : done) {
// System.out.println(technology.name());
// }
// }

// public void printUnitList(ArrayList<String> civUnits, ArrayList<String>
// milUnits) {
// System.out.println(Color.WHITE_BG.getColor() + Color.BLACK.getColor() + "Unit
// List" + Color.RESET.getColor());
// System.out.println("Civilian Units:");
// for (String unitInfo : civUnits) {
// System.out.println(unitInfo);
// }
// System.out.println("Military Units:");
// for (String unitInfo : milUnits) {
// System.out.println(unitInfo);
// }
// }

// public void printCitiesList(ArrayList<String> cities) {
// System.out.println(Color.WHITE_BG.getColor() + Color.BLACK.getColor() +
// "Cities List" + Color.RESET.getColor());
// for (String cityInfo : cities) {
// System.out.println(cityInfo);
// }
// }

// public void printDemographicInfo(String nickname, ArrayList<String> info) {
// System.out.println(
// Color.WHITE_BG.getColor() + Color.BLACK.getColor() + "Demographic Info" +
// Color.RESET.getColor());
// System.out.println("Player: " + nickname);
// System.out.println(" Size: " + info.get(0));
// System.out.println(" Population: " + info.get(1));
// System.out.println(" Happiness: " + info.get(2));
// System.out.println(" Gold: " + info.get(3));
// }

// public void printEconomicInfo(ArrayList<String> cities,
// ArrayList<ArrayList<String>> cityInfo) {
// System.out.println(
// Color.WHITE_BG.getColor() + Color.BLACK.getColor() + "Economic Info" +
// Color.RESET.getColor());
// for (String city : cities) {
// System.out.println(city);
// for (String info : cityInfo.get(cities.indexOf(city))) {
// System.out.println(" " + info);
// }
// }
// }

// public void printNotifications(ArrayList<String> messages) {
// System.out.println(
// Color.WHITE_BG.getColor() + Color.BLACK.getColor() + "Notification History:"
// + Color.RESET.getColor());
// for (String msg : messages) {
// System.out.println(msg);
// }
// }

// // print map
// public void printMap(String player, int totalHappiness, ArrayList<TileView>
// tiles, int y, int x) {
// System.out.println("Current Player: " + player);
// System.out.println("Total Happiness = " + totalHappiness);
// int temp = 0;
// // for (int i = 0; i < tiles.size(); i++) {
// // System.out.println(tiles.get(i).getX() + " " + tiles.get(i).getY());
// // }
// if (tiles.get(temp).getY() % 2 == 0) {
// for (int i = 0; i < 2 * x + 1; i++) {
// if (i % 2 == 0) {
// for (int j = 0; j < 3; j++) {

// for (int k = 2 - j; k > 0; k--) {
// System.out.print(Color.RESET.getColor() + " ");
// }

// for (int l = 0; l < y; l++) {
// if (l % 2 == 0) {
// if (temp < tiles.size()) {
// System.out.print(tiles.get(temp).getHasRiver().get(5) + "/");
// } else {
// System.out.print(Color.RESET.getColor() + "/");
// }
// if (j == 0) {
// if (temp < tiles.size()) {
// System.out.print(tiles.get(temp).getColor()[0]
// + tiles.get(temp).getBackgroundColor()
// + " " + Character.toUpperCase(tiles.get(temp).getNickname().charAt(0))
// + " ");
// } else {
// System.out.print(Color.RESET.getColor() + " ");
// }
// } else if (j == 1) {
// if (temp < tiles.size()) {
// System.out.print(tiles.get(temp).getBackgroundColor() + " ");
// System.out.printf("%02d,%02d", tiles.get(temp).getX(),
// tiles.get(temp).getY());
// System.out.print(tiles.get(temp).getBackgroundColor() + " ");
// } else {
// System.out.print(Color.RESET.getColor() + " ");
// }
// } else {
// if (temp < tiles.size()) {
// System.out.print(tiles.get(temp).getBackgroundColor() + " "
// + tiles.get(temp).getColor()[1]
// + tiles.get(temp).getCivilianUnit().substring(0, 3) + " "
// + tiles.get(temp).getColor()[2]
// + tiles.get(temp).getMilitaryUnit().substring(0, 3) + " ");
// } else {
// System.out.print(Color.RESET.getColor() + " ");
// }
// }
// if (temp < tiles.size()) {
// System.out.print(tiles.get(temp).getHasRiver().get(1) + "\\");
// } else {
// System.out.print(Color.RESET.getColor() + "\\");
// }
// } else {
// if (j == 0) {
// if (temp - y >= 0) {
// System.out.print(tiles.get(temp - y).getBackgroundColor() + " "
// + tiles.get(temp - y).getFeature().substring(0, 3) + " "
// + tiles.get(temp - y).getResourceTileView().substring(0, 3) + " ");
// } else {
// System.out.print(Color.RESET.getColor() + " ");
// }
// } else if (j == 1) {
// if (temp - y >= 0) {
// System.out.print(tiles.get(temp - y).getBackgroundColor() + " "
// + tiles.get(temp - y).getImprovement().substring(0, 3) + " ");
// } else {
// System.out.print(Color.RESET.getColor() + " ");
// }
// } else {
// if (temp - y >= 0) {
// System.out.print(tiles.get(temp - y).getHasRiver().get(3) + "-----");
// } else {
// System.out.print(Color.RESET.getColor() + "-----");
// }
// }
// }
// temp++;
// }
// temp -= (y);
// System.out.println(Color.RESET.getColor());
// }
// } else {
// for (int j = 0; j < 3; j++) {

// for (int k = j; k > 0; k--) {
// System.out.print(Color.RESET.getColor() + " ");
// }

// for (int l = 0; l < y; l++) {
// if (l % 2 == 0) {
// System.out.print(tiles.get(temp).getHasRiver().get(4) + "\\");
// if (j == 0) {
// System.out.print(tiles.get(temp).getBackgroundColor() + " "
// + tiles.get(temp).getFeature().substring(0, 3) + " "
// + tiles.get(temp).getResourceTileView().substring(0, 3) + " ");
// } else if (j == 1) {
// System.out.print(tiles.get(temp).getBackgroundColor() + " "
// + tiles.get(temp).getImprovement().substring(0, 3) + " ");
// } else {
// System.out.print(tiles.get(temp).getHasRiver().get(3) + "-----");
// }
// System.out.print(tiles.get(temp).getHasRiver().get(2) + "/");
// } else {
// if (j == 0) {
// System.out.print(tiles.get(temp).getColor()[0]
// + tiles.get(temp).getBackgroundColor()
// + " " + Character.toUpperCase(tiles.get(temp).getNickname().charAt(0))
// + " ");
// } else if (j == 1) {
// System.out.print(tiles.get(temp).getBackgroundColor() + " ");
// System.out.printf("%02d,%02d", tiles.get(temp).getX(),
// tiles.get(temp).getY());
// System.out.print(tiles.get(temp).getBackgroundColor() + " ");
// } else {
// System.out.print(
// tiles.get(temp).getBackgroundColor() + " " + tiles.get(temp).getColor()[1]
// + tiles.get(temp).getCivilianUnit().substring(0, 3) + " "
// + tiles.get(temp).getColor()[2]
// + tiles.get(temp).getMilitaryUnit().substring(0, 3) + " ");
// }
// }
// temp++;
// }
// temp -= y;
// System.out.println(Color.RESET.getColor());
// }
// temp += y;
// }
// }
// }

// else {
// temp = -y;
// for (int i = 0; i < 2 * x + 1; i++) {
// if (i % 2 == 0) {
// for (int j = 0; j < 3; j++) {

// for (int k = j; k > 0; k--) {
// System.out.print(Color.RESET.getColor() + " ");
// }

// for (int l = 0; l < y; l++) {
// if (l % 2 == 1) {
// if (temp + y < tiles.size()) {
// System.out.print(tiles.get(temp + y).getHasRiver().get(5) + "/");
// } else {
// System.out.print(Color.RESET.getColor() + "/");
// }
// if (j == 0) {
// if (temp + y < tiles.size()) {
// System.out.print(tiles.get(temp + y).getColor()[0]
// + tiles.get(temp + y).getBackgroundColor()
// + " "
// + Character.toUpperCase(tiles.get(temp + y).getNickname().charAt(0))
// + " ");
// } else {
// System.out.print(Color.RESET.getColor() + " ");
// }
// } else if (j == 1) {
// if (temp + y < tiles.size()) {
// System.out.print(tiles.get(temp + y).getBackgroundColor() + " ");
// System.out.printf("%02d,%02d", tiles.get(temp + y).getX(),
// tiles.get(temp + y).getY());
// System.out.print(tiles.get(temp + y).getBackgroundColor() + " ");
// } else {
// System.out.print(Color.RESET.getColor() + " ");
// }
// } else {
// if (temp + y < tiles.size()) {
// System.out.print(
// tiles.get(temp + y).getBackgroundColor() + " "
// + tiles.get(temp + y).getColor()[1]
// + tiles.get(temp + y).getCivilianUnit().substring(0, 3) + " "
// + tiles.get(temp + y).getColor()[2]
// + tiles.get(temp + y).getMilitaryUnit().substring(0, 3) + " ");
// } else {
// System.out.print(Color.RESET.getColor() + " ");
// }
// }
// if (temp + y < tiles.size()) {
// System.out.print(tiles.get(temp + y).getHasRiver().get(1) + "\\");
// } else {
// System.out.print(Color.RESET.getColor() + "\\");
// }
// } else {
// if (j == 0) {
// if (temp >= 0) {
// System.out.print(tiles.get(temp).getBackgroundColor() + " "
// + tiles.get(temp).getFeature().substring(0, 3) + " "
// + tiles.get(temp).getResourceTileView().substring(0, 3) + " ");
// } else {
// System.out.print(Color.RESET.getColor() + " ");
// }
// } else if (j == 1) {
// if (temp >= 0) {
// System.out.print(tiles.get(temp).getBackgroundColor() + " "
// + tiles.get(temp).getImprovement().substring(0, 3) + " ");
// } else {
// System.out.print(Color.RESET.getColor() + " ");
// }
// } else {
// if (temp >= 0) {
// System.out.print(tiles.get(temp).getHasRiver().get(3) + "-----");
// } else {
// System.out.print(Color.RESET.getColor() + "-----");
// }
// }
// }
// temp++;
// }
// temp -= (y);
// System.out.println(Color.RESET.getColor());
// }
// temp += y;
// } else {
// for (int j = 0; j < 3; j++) {

// for (int k = 2 - j; k > 0; k--) {
// System.out.print(Color.RESET.getColor() + " ");
// }

// for (int l = 0; l < y; l++) {
// if (l % 2 == 1) {
// System.out.print(tiles.get(temp).getHasRiver().get(4) + "\\");
// if (j == 0) {
// System.out.print(tiles.get(temp).getBackgroundColor() + " "
// + tiles.get(temp).getFeature().substring(0, 3) + " "
// + tiles.get(temp).getResourceTileView().substring(0, 3) + " ");
// } else if (j == 1) {
// System.out.print(tiles.get(temp).getBackgroundColor() + " "
// + tiles.get(temp).getImprovement().substring(0, 3) + " ");
// } else {
// System.out.print(tiles.get(temp).getHasRiver().get(3) + "-----");
// }
// System.out.print(tiles.get(temp).getHasRiver().get(2) + "/");
// } else {
// if (j == 0) {
// System.out.print(tiles.get(temp).getColor()[0]
// + tiles.get(temp).getBackgroundColor()
// + " " + Character.toUpperCase(tiles.get(temp).getNickname().charAt(0))
// + " ");
// } else if (j == 1) {
// System.out.print(tiles.get(temp).getBackgroundColor() + " ");
// System.out.printf("%02d,%02d", tiles.get(temp).getX(),
// tiles.get(temp).getY());
// System.out.print(tiles.get(temp).getBackgroundColor() + " ");
// } else {
// System.out.print(
// tiles.get(temp).getBackgroundColor() + " " + tiles.get(temp).getColor()[1]
// + tiles.get(temp).getCivilianUnit().substring(0, 3) + " "
// + tiles.get(temp).getColor()[2]
// + tiles.get(temp).getMilitaryUnit().substring(0, 3) + " ");
// }
// }
// temp++;
// }
// temp -= y;
// System.out.println(Color.RESET.getColor());
// }
// }
// }
// }
// }
// }
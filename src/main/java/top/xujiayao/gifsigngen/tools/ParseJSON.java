package top.xujiayao.gifsigngen.tools;

import javafx.application.Platform;
import top.xujiayao.gifsigngen.ui.Dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xujiayao
 */
public class ParseJSON {

	private ParseJSON() {
		throw new IllegalStateException("工具类");
	}

	/*
	0: favAirport
	1: favRoute
	 */
	public static String[] parseLogbookJSON(String data) {
		if (data == null) {
			return new String[0];
		}

		String[] datas = new String[2];

		try {
			HashMap<String, Integer> airportMap = new HashMap<>();
			HashMap<String, Integer> routeMap = new HashMap<>();

			List<Map.Entry<String, Integer>> list;

			int airportNum = Utils.findStrOccurrence("{\"name\":\"", data);
			int counter;
			String temp = null;
			String hashInsertTemp;
			String[] routes = new String[airportNum / 2];

			for (int i = 0; i < airportNum; i++) {
				data = data.substring(9).substring(data.indexOf("{\"name\":\""));
				hashInsertTemp = data.substring(0, data.indexOf("\"")).replace("\\/", "/");

				if (airportMap.containsKey(hashInsertTemp)) {
					counter = airportMap.get(hashInsertTemp);
					airportMap.put(hashInsertTemp, ++counter);
				} else {
					airportMap.put(hashInsertTemp, 1);
				}

				if (temp == null) {
					temp = data.substring(0, data.indexOf("\\/"));
					continue;
				}

				routes[(i + 1) / 2 - 1] = temp + " - " + data.substring(0, data.indexOf("\\/"));
				temp = null;
			}

			list = new ArrayList<>(airportMap.entrySet());

			list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

			list.stream().findFirst().ifPresent(mapping -> datas[0] = mapping.getKey().replace("\\/", "/"));

			list.clear();

			for (String route : routes) {
				hashInsertTemp = route.replace("\\/", "/");

				if (routeMap.containsKey(hashInsertTemp)) {
					counter = routeMap.get(hashInsertTemp);
					routeMap.put(hashInsertTemp, ++counter);
				} else {
					routeMap.put(hashInsertTemp, 1);
				}
			}

			list = new ArrayList<>(routeMap.entrySet());

			list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

			list.stream().findFirst().ifPresent(mapping -> datas[1] = mapping.getKey().replace("\\/", "/"));
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return datas;
	}

	/*
	0: visitedPercentage
	 */
	public static String parsePassportJSON(String data) {
		if (data == null) {
			return "";
		}

		String visitedPercentage = null;

		try {
			visitedPercentage = String.format("%.1f", ((double) Utils.findStrOccurrence("\"visited\":1", data) * 100 / (double) Utils.findStrOccurrence("\"visited\":", data))) + "%";

		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return visitedPercentage;
	}

	/*
	0: bio
	1: completed_achievements
	2: total_flights
	3: total_hours
	4: registration
	5: aircraft_type
	6: average_landing_rate
	 */
	public static String[] parseProfileJSON(String data) {
		if (data == null) {
			return new String[0];
		}

		String[] datas = new String[7];

		try {
			data = data.substring(data.indexOf("\"bio\":") + 7);
			datas[0] = Utils.unicodeToString(data.substring(0, data.indexOf("\"")));

			datas[1] = Utils.findStrOccurrence("\"complete\":true", data) + " / " + Utils.findStrOccurrence("\"complete\":", data);

			data = data.substring(data.indexOf("total_flights\"") + 16);
			datas[2] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("total_hours\"") + 14);
			datas[3] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("registration\"") + 15);
			datas[4] = Utils.unicodeToString(data.substring(0, data.indexOf("\"")));

			data = data.substring(data.indexOf("aircraft_type\"") + 16);
			datas[5] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("average_landing_rate\"") + 23);
			datas[6] = data.substring(0, data.indexOf("\"")) + " fpm";
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return datas;
	}

	/*
	0: username
	1: display_name
	2: avatar
	3: account_type
	4: roles
	5: colour
	6: token
	 */
	public static String[] parseLoginJSON(String data) {
		if (data == null) {
			return new String[0];
		}

		String[] datas = new String[7];

		try {
			data = data.substring(44);
			datas[0] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("display_name\"") + 15);
			datas[1] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("avatar\"") + 9);
			datas[2] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("account_type\"") + 23);
			datas[3] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("roles\"") + 17);
			datas[4] = data.substring(0, data.indexOf(" "));

			data = data.substring(data.indexOf("colour\"") + 9);
			datas[5] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("token\"") + 8);
			datas[6] = data.substring(0, data.indexOf("\""));
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return datas;
	}

	/*
	0: releaseVersion
	1: releaseDate
	2: releaseData
	3: releaseSize
	4: releaseSplit
	5: releaseLink
	6: betaVersion
	7: betaDate
	8: betaData
	9: betaSize
	10: betaSplit
	11: betaLink
	 */
	public static String[] parseUpdateJSON(String data) {
		if (data == null) {
			return new String[0];
		}

		String[] datas = new String[12];

		try {
			data = data.substring(23);
			datas[0] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[1] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[2] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[3] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(11);
			datas[4] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[5] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(22);
			datas[6] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[7] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[8] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[9] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(11);
			datas[10] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[11] = data.substring(0, data.indexOf("\""));
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return datas;
	}

	/*
	0: version
	1: data
	2: link
	*/
	public static String[] parseUploadJSON(String data) {
		if (data == null) {
			return new String[0];
		}

		String[] datas = new String[7];

		try {
			data = data.substring(11);
			datas[0] = data.substring(0, data.indexOf(","));

			data = data.substring(data.indexOf("\"")).substring(8);
			datas[1] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("message")).substring(10);
			datas[2] = data.substring(0, data.indexOf("\""));

			switch (datas[1]) {
				case "success" -> {
					data = data.substring(data.indexOf("url")).substring(6);
					datas[3] = data.substring(0, data.indexOf("\""));

					data = data.substring(data.indexOf("delete")).substring(9);
					datas[4] = data.substring(0, data.indexOf("\""));

					datas[5] = "[img=3200,200]" + datas[3] + "[/img]";

					datas[6] = "![signature.gif](" + datas[3] + ")";
				}
				case "image_repeated" -> {
					data = data.substring(data.indexOf("images")).substring(9);
					datas[3] = data.substring(0, data.indexOf("\""));

					datas[4] = "N/A";

					datas[5] = "[img=3200,200]" + datas[3] + "[/img]";

					datas[6] = "![signature.gif](" + datas[3] + ")";
				}
				default -> {
					datas[3] = "N/A";
					datas[4] = "N/A";
					datas[5] = "N/A";
					datas[6] = "N/A";
				}
			}
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return datas;
	}

	/*
	0: hitokoto
	1: from
	*/
	public static String[] parseHitokotoJSON(String data) {
		if (data == null) {
			return new String[0];
		}

		String[] datas = new String[2];

		try {
			data = data.substring(data.indexOf("hitokoto")).substring(11);
			datas[0] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("from")).substring(7);
			datas[1] = data.substring(0, data.indexOf("\""));
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return datas;
	}
}

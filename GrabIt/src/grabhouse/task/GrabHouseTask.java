/**
 * 
 */
package grabhouse.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author apasha
 *
 */
public class GrabHouseTask {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int T = Integer.parseInt(line);
		for (int i = 0; i < T; i++) {
			Integer numOfHouses = Integer.parseInt(br.readLine());
			LinkedList<Integer> costList = new LinkedList<>();
			String costOfHousesStr[] = br.readLine().split(" ");
			if(costOfHousesStr.length != numOfHouses){
				System.out.println("Number of houses and no of costs are not same");
			}
			for (String str : costOfHousesStr) {
				costList.add(Integer.valueOf(str));
			}
			printMinTimeRequiredToBuy(costList);
		}
	}

	private static void printMinTimeRequiredToBuy(LinkedList<Integer> costList) {
		if(!(costList.size() > 2)){
			System.out.println(costList.get(0)+costList.get(1));
			return;
		}
		
		// Min = Left*Current + Right*Current – Left*Right
		Integer min = 0;
		int index = -1;
		Map<Integer, Integer> map = new TreeMap<>();
		while (costList.size() > 2) {
			Integer minIteration = Integer.MAX_VALUE; 
			for (int i = 1; i < costList.size()-1; i++) {
				if (i != 0 || i != costList.size()-1) {
					int minValueForI = (costList.get(i - 1) * costList.get(i) + costList.get(i + 1) * costList.get(i))
							- (costList.get(i - 1) * costList.get(i + 1));
					if (minValueForI < minIteration) {
						minIteration = minValueForI;
						index = i;
					}
				}
			}
			costList.remove(index);
			min = min + minIteration;
		}
		System.out.println(min);

	}

	private static LinkedList<Integer> getLinkedList(Integer[] costOfHouses) {
		LinkedList<Integer> list = new LinkedList<>();
		for (Integer cost : costOfHouses) {
			list.add(cost);
		}
		return list;
	}

	private static Integer getMinValue(int index, Integer[] costOfHouses) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < costOfHouses.length; i++) {
			if (i != 0 || i != costOfHouses.length) {
				int min = (costOfHouses[i - 1] * costOfHouses[i] + costOfHouses[i + 1] * costOfHouses[i])
						- (costOfHouses[i - 1] * costOfHouses[i + 1]);
				map.put(min, i);
			}
		}
		return null;
	}

}

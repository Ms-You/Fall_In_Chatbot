import java.util.*;

public class Test {
	
	public static void main(String[] args) {
		
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, List<Integer>> submap = new HashMap<>();
		List<Integer> semiList = new ArrayList<>();
		
		semiList.add(10);
		semiList.add(11);
		semiList.add(12);
		
		List<Integer> finalList = new ArrayList<>();
		finalList.add(11);
		finalList.add(12);
		finalList.add(23);
		
		submap.put("semi", semiList);
		submap.put("final", finalList);
		
		map.put("person", "���");
		map.put("sports","�߱�");
		map.put("score", submap);
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("person", "���2");
		map2.put("sports", "�౸2");
		
		Map<String, Object> map3 = new HashMap<>();
		map3.put("person", "���3");
		map3.put("sports", "��");
		
		list.add(map);
		list.add(map2);
		list.add(map3);
		
		System.out.println(list.toString());
	
		//2. 첫번째 사람의 두번째 값을 출력 - 11이 나와야함
		Map<String, Object> res = list.get(0);
		Map<String, List<Integer>> scoreMap = (Map<String, List<Integer>>)map.get("score");
		
		List<Integer> semi = scoreMap.get("semi");
		int value = semi.get(1);
		
		System.out.println(value);
	}
	
}

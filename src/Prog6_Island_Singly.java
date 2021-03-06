
class SinglyCircularLinkedList {
	Node first;
	Node last;
	int size;
	
	public SinglyCircularLinkedList() {
		first = new Node(null);
		last = new Node(null);
		size = 0;
	}
	
	class Node {
		String value;
		int number;
		int index;
		Node next;
		
		public Node(String value) {
			this.value = value;
			this.number = size + 1;
			this.index = 0;
			this.next = null;
		}
	}
	
	void setLast() {
		Node currentLast = getNode(size-1);
		currentLast.next = first.next;
		last = currentLast;
	}
	
	void updateIndex() {
		int i = 0;
		if (last != null) {
			Node temp = last;
			temp = temp.next;
			temp.index = i++;
			do {
				temp = temp.next;
				temp.index = i++;
			} while(temp != last);
		}
	}
	
	Node getNode(int index) {
		if (index < 0 || index >= size) {
			System.out.println("Error : getNode");
			return null;
		}
		
		Node node = first.next;
		for(int i = 0 ; i < index ; i++) {
			node = node.next;
		}
		return node;
	}
	
	void insertFront(String val) {
		Node node = new Node(val);
		node.next = first.next;
		first.next = node;
		size++;
		setLast();
		updateIndex();
	}
	
	void insertMiddle(int index, String val) {
		if (index == 0) {
			insertFront(val);
			return;
		}
		Node post = getNode(index - 1);
		Node next = post.next;
		
		Node node = new Node(val);
		post.next = node;
		node.next = next;
		size++;
		setLast();
		updateIndex();
	}
	
	void insertRear(String val) {
		insertMiddle(size, val);
	}
	
	String deleteFront() {
		Node frontNode = getNode(0);
		first.next = frontNode.next;
		size--;
		setLast();
		updateIndex();
		return frontNode.value;
	}
	
	String deleteMiddle(int index) {
		if (index < 0 || index >= size) {
			System.out.println("Error : deleteMiddle");
			return null;
		}
		else if (index == 0) {
			return deleteFront();
		}
		
		Node post = getNode(index - 1);
		Node deleteNode = post.next;
		Node nextNode = deleteNode.next;
		post.next = nextNode;
		size--;
		setLast();
		updateIndex();
		return deleteNode.value;
	}
	
	String deleteRear() {
		return deleteMiddle(size - 1);
	}
	
	int size() {
		return size;
	}
	
	void print_list() {
		if (last != null) {
			Node temp = last;
			temp = temp.next;
			System.out.printf("%d - %s\n", temp.number, temp.value);
			do {
				temp = temp.next;
				System.out.printf("%d - %s\n", temp.number, temp.value);
			} while(temp != last);
		}
	}
	
	void selectPerson() {
		int random = (int) (Math.random() * 30) + 1;
		Node temp = getNode(random - 1);
		System.out.println("[제비뽑기 결과]");
		System.out.println(temp.number+"번의 "+temp.value+"님이 선택되었습니다.");
		System.out.println();
		int count = 1;
		while (size > 7) {
			for (int i = 0 ; i < 5 ; i++)
				temp = temp.next;
			System.out.println("[알림]"+temp.number+"번의 "+temp.value+"님이 생존자에서 제외되었습니다.");
			deleteMiddle(temp.index);
			if (size == 7) {
				System.out.println("[최후 7인 리스트]");
				print_list();
				break;
			}
			System.out.printf("[%d번 째 생존자 리스트]\n", count++);
			print_list();
			System.out.println();
			temp = temp.next;
		}
	}
}

public class Prog6_Island_Singly {
	static String[] inputArray = {"Hyojeong", "Jiwan", "Yeongeun",
			"Jongsuk", "Jeonghun", "Heewon",
			"Youngho", "Minji", "Hojun",
			"Sumin", "Yoonsang", "Yerin",
			"Jina", "Gayoung", "Jeongyoung",
			"Junho", "Youngwoo", "Yoonhee",
			"Bonghee", "Sanggu", "Bruno",
			"Samkim", "Eddykim", "Dongguen",
			"Samuel", "Saeyoung", "Minseon",
			"Suyeon", "Sunghun", "Ayeon",
			"John"};
	
	
	public static void main(String[] args) {
		SinglyCircularLinkedList clist = new SinglyCircularLinkedList();
		
		for (String s : inputArray) {
			clist.insertRear(s);
		}
		System.out.println("[조난자 리스트 생성 완료]");
		clist.print_list();
		
		clist.selectPerson();
	}
}

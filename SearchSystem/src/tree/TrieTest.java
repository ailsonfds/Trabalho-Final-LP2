package tree;

import java.util.Map;

//TESTE APENAS
public class TrieTest {

	public TrieTest() {
		// TODO Auto-generated constructor stub
	}

	//ARRUMAR
	public static void main(String[] args) {
		Trie w = new Trie();
		w.insert("testes");
		w.insert("larissa");
		
		if (w.search("testes") & w.search("larissa"))
		{
			Node node = w.getRoot();

			Map<Character, Node> map = node.getChildrens();
			for (Map.Entry<Character, Node> entry : map.entrySet()) {
				Node tNode = entry.getValue();
				print(tNode, "");
			}
		}
		w.remove("larissa");
		w.remove("larissa");
	}
	
	//ARRUMAR
	public static void print (Node node, String t)
	{
        Map<Character, Node> map = node.getChildrens();
       
        t = t+node;
        
        if(node.getInfo()){
            System.out.println(t);
        }
        if(map == null){
            return;
        }
        for(Map.Entry<Character, Node> entry : map.entrySet()){
        	print(entry.getValue(), t);
        }
	}
}

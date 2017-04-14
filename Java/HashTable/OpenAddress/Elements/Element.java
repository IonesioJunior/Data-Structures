package HashTable.OpenAddress.Elements;

/**
 * Generic elements
 * */
public class Element implements Storable{
	private Integer key;
	
	public Element(int key){
		this.key = key;
	}
	
	@Override
	public int hashCode(){
		return this.key.hashCode();
	}
	@Override
	public boolean equals(Object obj){
		return this.hashCode() == obj.hashCode();
	}
}

package SkipList;
/*
 * @author Ionésio Junior
 * */
/*
 * Skip List Implementation
 * */
public class SkipList<T extends Comparable<T>> implements SkipListInterface<T> {
	private SkipListNode<T> root;
	private SkipListNode<T> NIL;
	private int height;
	
	public SkipList(int maxHeight){
		this.height = maxHeight;
		this.root   = new SkipListNode<T>(Integer.MIN_VALUE, maxHeight, null);
		this.NIL = new SkipListNode<T>(Integer.MAX_VALUE, maxHeight,null);
		connectRootToNil();
	}
	
	private void connectRootToNil(){
		for(int i = 0 ; i < root.getForward().length;i++){
			root.getForward()[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
		if(newValue != null){
			this.ajustHeight(height);
			SkipListNode<T>[] previousNodes = new SkipListNode[height];
			SkipListNode<T> aux = this.root;
			for(int i = height - 1 ; i >= 0;i--){
				while(aux.getForward(i).getValue() != null && aux.getForward(i).getKey() < key){
					aux = aux.getForward(i);
				}
				previousNodes[i] = aux;
			}
			aux = aux.getForward(0);
			if(aux.getKey() == key){
				aux.setValue(newValue);
			}else{
				aux = new SkipListNode<T>(key, height, newValue);
				changePointers(height,previousNodes,aux);
			}
		}
	}

     @Override
     public void remove(int key) {
        SkipListNode[] array = new SkipListNode[this.height];
        SkipListNode<T> aux = this.root;
        for (int i = this.height - 1; i >= 0; i--) {
           if (aux.getForward(i) != this.NIL) {
              while (aux.getForward(i).getValue() != null && aux.getForward(i).getKey() < key)
                 aux = aux.getForward(i);
           }
           array[i] = aux;
        }
        aux = aux.getForward()[0];
  
        if (aux.getKey() == key) {
  
           for (int i = 0; i < this.height; i++) {
              if (array[i].getForward()[i] != aux) {
                 break;
              }
             array[i].getForward()[i] = aux.getForward()[i];
          }
       }
    }

	@Override
	public int height() {
		int height = this.height - 1;
		while(height >= 0 && this.root.getForward(height) == NIL){
			height--;
		}
		return height+ 1;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> aux = this.root;
		for(int i = this.height - 1;i >= 0;i--){
			while(aux.getForward(i).getValue() != null && aux.getForward(i).getKey() < key){
				aux = aux.getForward(i);
			}
		}
		SkipListNode<T> result = aux.getForward(0);
		if(result.getKey() != key){
			return null;
		}else{
			return result;
		}
	}

	@Override
	public int size() {
		int count = 0; 
		SkipListNode<T> aux  = this.root.getForward(0);
		while(aux != NIL){
			count++;
			aux = aux.getForward(0);
		}
		return count;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		int size = this.size() + 2;
		SkipListNode<T>[] result = new SkipListNode[size];
		SkipListNode<T> aux = this.root;
		int index = 0;
		 while(index < size){
			 result[index++] = aux;
			 aux = aux.getForward(0);
		 }
		 return result;
	}
	
	///////////////////////   AUXILIAR METHODS		//////////////////////////////
	
	private void ajustHeight(int height){
		if(height > this.height){
			SkipListNode<T>[] rootArray = new SkipListNode[height];
			this.NIL.setForward(new SkipListNode[height]);
			for(int i = 0; i < height;i++){
				if(i < this.height){
					rootArray[i] = this.root.getForward(i);
				}else{
					rootArray[i] = this.NIL;
				}
			}
			this.root.setForward(rootArray);
			this.height = height;
		}
	}
	
	
	private void changePointers(int height,SkipListNode[] previousNodes,SkipListNode<T> next){
		for(int i = 0; i < height;i++){
			if(previousNodes[i].getForward(i) == null){
				next.getForward()[i] = NIL;
			}else{
				next.setForward(i, previousNodes[i].getForward(i));
				previousNodes[i].setForward(i, next);
			}
		}
	}
	
}

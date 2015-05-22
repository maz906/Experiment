package dynamicList;

import java.util.ArrayList;
import java.util.Collection;

public class DynamicTypeList {
	private ArrayList<Wrapper> list;
	
	public DynamicTypeList() {
		list = new ArrayList<Wrapper>();
	}
	
	public DynamicTypeList(int size) {
		list = new ArrayList<Wrapper>(size);
	}
	
	public <T> DynamicTypeList(Collection<T> collec) {
		for (T item : collec)
			this.add(item);
	}
	
	public <T> boolean add(T item) {
		return list.add(new WrapperType<T>(item));		
	}
	
	public <T> boolean addAll(Collection<T> collec) {
		int prevSize = list.size();
		
		for (T item : collec)
			if (item instanceof Wrapper)
				list.add((Wrapper) item);
			else
				this.add(item);
		
		return prevSize == list.size();
	}
	
	public void clear() {
		list.clear();
	}
	
	public boolean contains(Object o) {
		if (o instanceof Wrapper)
			return list.contains((Wrapper) o);
		
		return list.contains(new WrapperType<Object>(o));
	}
	
	public <T> boolean containsAll(Collection<T> collec) {
		for (T item : collec)
			if (this.contains(item))
				return true;
		
		return false;
	}
	
	public Object get(int idx) {
		return list.get(idx).data();
	}
	
	public int hashCode() {
		return list.hashCode();
	}
	
	public int indexOf(Object o) {
		if (o == null)
			return -1;
		
		Wrapper temp = null;
		if (o instanceof Wrapper) 
			temp = (Wrapper) o;
		
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).data().equals(o) || list.get(i).data().equals(temp.data()))
				return i;
		
		return -1;
		
	}
	
	public Object remove(int index) {
		return list.remove(index).data();
	}
	
	public boolean remove(Object o) {
		if (o instanceof Wrapper) {
			return this.remove(((Wrapper) o).data());
		}
		
		for (Wrapper w : list)
			if (w.data().equals(o))
				return list.remove(w);
		
		return false;				
	}
	
	public int size() {
		return list.size();
	}
}

package dynamicList;

public class WrapperType<T> implements Wrapper {
	T data;
	
	public WrapperType(T _data) {
		data = _data;
	}
	
	@SuppressWarnings("unchecked")
	public T data() {
		return data;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof WrapperType<?>))
			return false;
		
		WrapperType<?> temp = (WrapperType<?>) o;
		
		return temp.data().equals(data);
	}
}

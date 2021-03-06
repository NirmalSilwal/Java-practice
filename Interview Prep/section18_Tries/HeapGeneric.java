package section18_Tries;

import java.util.ArrayList;

public class HeapGeneric<T extends Comparable<T>> {

	ArrayList<T> data;
	boolean isMin;

	HeapGeneric() {
		this(false);
	}

	HeapGeneric(boolean isMin) {
		this.isMin = isMin;
		this.data = new ArrayList<>();
	}

	public void display() {
		System.out.println(data);
	}

	public int size() {
		return this.data.size();
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public T get() {
		return this.data.get(0);
	}

	public void add(T item) {
		data.add(item);
		upheapify(data.size() - 1);
	}

	private void upheapify(int childIndex) {
		int parentIndex = (childIndex - 1) / 2;

		if (isLarger(data.get(childIndex), data.get(parentIndex)) > 0) {
			this.swap(childIndex, parentIndex);
			upheapify(parentIndex);
		}
	}

	private void swap(int i, int j) {
		T ithVal = data.get(i);
		T jthVal = data.get(j);

		data.set(i, jthVal);
		data.set(j, ithVal);
	}

	public T remove() throws Exception {
		if (this.size() == 0)
			throw new Exception("No elements to remove...");

		T removedItem = data.get(0);

		this.swap(0, this.size() - 1);
		data.remove(this.size() - 1);

		downheapify(0);

		return removedItem;
	}

	public void downheapify(int pi) {
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;

		int minIndex = pi;

		if (lci < this.size() && (isLarger(data.get(lci), data.get(minIndex)) > 0))
			minIndex = lci;

		if (rci < this.size() && (isLarger(data.get(rci), data.get(minIndex)) > 0))
			minIndex = rci;

		if (minIndex != pi) {
			swap(minIndex, pi);
			downheapify(minIndex);
		}
	}

	public int isLarger(T thisRef, T otherRef) {
		return thisRef.compareTo(otherRef);
	}
}

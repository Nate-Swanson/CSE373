package datastructures.concrete;

import datastructures.interfaces.IList;
import misc.exceptions.EmptyContainerException;
import misc.exceptions.NotYetImplementedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Note: For more info on the expected behavior of your methods, see
 * the source code for IList.
 */
public class DoubleLinkedList<T> implements IList<T> {
    // You may not rename these fields or change their types.
    // We will be inspecting these in our private tests.
    // You also may not add any additional fields.
    private Node<T> front;
    private Node<T> back;
    private int size;

    public DoubleLinkedList() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    @Override
    public void add(T item) {
    		Node newNode = new Node(item);
    		if(front == null) {
    			if(back !=null) {
    				throw new AssertionError("back is occupied");
    			}
    			front = newNode;
    			back = front;
    			this.size++;
    			
    		}
    		else {
    			back.setNextNode(newNode);
    			newNode.setNextNode(null);
    			back = newNode;
    			this.size++;
    			
    		}
    		
    }
    
    /**
     * Removes and returns the item from the *end* of this IList.
     *
     * @throws EmptyContainerException if the container is empty and there is no element to remove.
     */

    @Override
    public T remove() {
    		if(this.size==0) {
    			throw new EmptyContainerException();
    		}
    		Node lastNode = front;
    		Node previousToLast = null;
    		if(front.next == null) {
    			front = null;
    			back = null;
    		}
    		while(lastNode.next != null) {
    			previousToLast = lastNode;
    			lastNode = lastNode.next;
    		}
    		if(front !=null) {
    			previousToLast.next = null;
    		}
    		this.size--;    		
    		return (T) lastNode.data;
    }

    
    @Override
    public T get(int index) {
    	if(index <0 || index>=this.size) {
			throw new IndexOutOfBoundsException();
		}
    	Node current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return (T) current.data;
    }

    /**
     * Overwrites the element located at the given index with the new item.
     *
     * @throws IndexOutOfBoundsException if the index < 0 or index >= this.size()
     */
    @Override
    public void set(int index, T item) {
    		if(index <0 || index>=this.size) {
			throw new IndexOutOfBoundsException();
		}
    		Node current = front;
    		if(index == 0) {
    			if(front.next!=null) {
    				front = new Node(item);
    				front.next = current.next;
    			}
    			else {
    				front = new Node(item);
    			}
    			return;
    		}
    		int counter = 1;
    		Node previous = front;
    		current = current.next;
    		while(counter <= index) {
    			if(counter == index) {
    				Node currentBeforeReplace = current;
    				if(current.next != null) {
        				current = new Node(item);
        				previous.next = current;
        				current.next = currentBeforeReplace.next;
    				}
    				else {
    					current = new Node(item);
        				previous.next = current;
        				current.next = null;
    				}
    				break;
    			}
    			current = current.next;
    			previous = previous.next;
    			counter++;
    		}
    		
    }
    
    /**
     * Inserts the given item at the given index. If there already exists an element
     * at that index, shift over that element and any subsequent elements one index
     * higher.
     *
     * @throws IndexOutOfBoundsException if the index < 0 or index >= this.size() + 1
     */

    @Override
    public void insert(int index, T item) {
    		if(index < 0 || index >= this.size()+1) {
    			throw new IndexOutOfBoundsException();
    		}
    		Node previousHead = front;
    		if(index == 0) {
    			Node newHead = new Node(item);
    			front = newHead;
    			front.next = previousHead;
    			this.size++;
    		}
    		else {
    			int counter = 1;
    			Node prevoiusNode = front;
    			Node currentNode = front.next;
    			while(counter <= index) {
    				if(counter == index) {
    					if(index > this.size-1) {
    						Node newNode = new Node(item);
    						prevoiusNode.next = newNode;
    						newNode.next = null;
    						this.size++;
    						break;
					}
						
    					if(currentNode.next != null) {
    						Node newNode = new Node(item);
    						prevoiusNode.next = newNode;
    						newNode.next = currentNode;
    						this.size++;
    					}
    					else {
    						Node newNode = new Node(item);
    						prevoiusNode.next = newNode;
    						newNode.next = null;
    						this.size++;
    					}
    				}
    				prevoiusNode = currentNode;
    				currentNode = currentNode.next;
    				counter++;
    			}
    		}
        //throw new NotYetImplementedException();
    }

    /**
     * Deletes the item at the given index. If there are any elements located at a higher
     * index, shift them all down by one.
     *
     * @throws IndexOutOfBoundsException if the index < 0 or index >= this.size()
     */
    
    @Override
    public T delete(int index) {
    		if(index<0 || index >= this.size()) {
    			throw new IndexOutOfBoundsException();
    		}
    		return null;
        //throw new NotYetImplementedException();
    }

    @Override
    public int indexOf(T item) {
        throw new NotYetImplementedException();
    }

    @Override
    public int size() {
    		return this.size;
        //throw new NotYetImplementedException();
    }

    @Override
    public boolean contains(T other) {
        throw new NotYetImplementedException();
    }
    
    

    @Override
    public Iterator<T> iterator() {
        // Note: we have provided a part of the implementation of
        // an iterator for you. You should complete the methods stubs
        // in the DoubleLinkedListIterator inner class at the bottom
        // of this file. You do not need to change this method.
        return new DoubleLinkedListIterator<>(this.front);
    }

    private static class Node<E> {
        // You may not change the fields in this node or add any new fields.
        public final E data;
        public Node<E> prev;
        public Node<E> next;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Node(E data) {
            this(null, data, null);
        }

        // Feel free to add additional constructors or methods to this class.
        
        //ADDITIONAL METHODS
        private E getData() {
        		return this.data;
        }
        private void setNextNode(Node n) {
        		this.next = n;
        }
        private Node getNextNode() {
        		return this.next;
        }
      //END OF ADDITIONAL METHODS
        
    }

    private static class DoubleLinkedListIterator<T> implements Iterator<T> {
        // You should not need to change this field, or add any new fields.
        private Node<T> current;

        public DoubleLinkedListIterator(Node<T> current) {
            // You do not need to make any changes to this constructor.
            this.current = current;
        }

        /**
         * Returns 'true' if the iterator still has elements to look at;
         * returns 'false' otherwise.
         */
        public boolean hasNext() {
            throw new NotYetImplementedException();
        }

        /**
         * Returns the next item in the iteration and internally updates the
         * iterator to advance one element forward.
         *
         * @throws NoSuchElementException if we have reached the end of the iteration and
         *         there are no more elements to look at.
         */
        public T next() {
            throw new NotYetImplementedException();
        }
    }
}

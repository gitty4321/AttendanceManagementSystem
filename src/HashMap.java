public class HashMap {
    private static class Entry {
        String key;
        boolean value;
        Entry next;

        public Entry(String key, boolean value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private final Entry[] table;
    private final int capacity;

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(String key, boolean value) {
        int index = hash(key);
        Entry newEntry = new Entry(key, value);
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value;
            }
            else {
                current.next = newEntry;
            }
        }
    }

    public boolean get(String key) {
        int index = hash(key);
        Entry current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return false;
    }

    public boolean containsKey(String key) {
        int index = hash(key);
        Entry current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
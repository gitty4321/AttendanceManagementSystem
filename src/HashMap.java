class HashMap {
    private static class Entry {
        String key;
        String name;
        boolean value;
        Entry next;

        public Entry(String key, String name, boolean value) {
            this.key = key;
            this.name = name;
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

    public void put(String key, String name, boolean value) {
        int index = hash(key);
        Entry newEntry = new Entry(key, name, value);
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.name = name;
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.name = name;
                current.value = value;
            } else {
                current.next = newEntry;
            }
        }
    }

    public String getName(String key) {
        int index = hash(key);
        Entry current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.name;
            }
            current = current.next;
        }
        return null;
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

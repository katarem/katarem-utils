package io.github.katarem;

public class Pair<A,B> {
    private A first;
    private B second;

    public A getFirst(){
        return first;
    }
    public B getSecond(){
        return second;
    }

    @SuppressWarnings("unused")
    private Pair(){}

    public Pair(A first, B second){
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pair other = (Pair) obj;
        if (first == null) {
            if (other.first != null)
                return false;
        } else if (!first.equals(other.first))
            return false;
        if (second == null) {
            if (other.second != null)
                return false;
        } else if (!second.equals(other.second))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Pair(" + first + "," + second + ")";
    }

    @SuppressWarnings("rawtypes")
    public static final Pair EMPTY = new Pair<>(null, null);

    public boolean isEmpty(){
        return this.first == null || this.second == null;
    }

}

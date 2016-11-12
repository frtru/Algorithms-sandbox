package core.utils;

//The MIT License (MIT)
//Copyright (c) 2016 Peter Lawrey
//@ http://stackoverflow.com/questions/156275/what-is-the-equivalent-of-the-c-pairl-r-in-java

//Permission is hereby granted, free of charge, 
//to any person obtaining a copy of this software 
//and associated documentation files (the "Software"), 
//to deal in the Software without restriction, 
//including without limitation the rights to use, 
//copy, modify, merge, publish, distribute, sublicense, 
//and/or sell copies of the Software, and to permit 
//persons to whom the Software is furnished to do so, 
//subject to the following conditions:

//The above copyright notice and this permission 
//notice shall be included in all copies or 
//substantial portions of the Software.

//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY 
//OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
//LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
//FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO 
//EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
//FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
//AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
//OR OTHER DEALINGS IN THE SOFTWARE.

public class Pair<FIRST, SECOND> implements Comparable<Pair<FIRST, SECOND>> {

    public final FIRST first;
    public final SECOND second;

    private Pair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }

    public static <FIRST, SECOND> Pair<FIRST, SECOND> of(FIRST first,
            SECOND second) {
        return new Pair<FIRST, SECOND>(first, second);
    }

    @Override
    public int compareTo(Pair<FIRST, SECOND> o) {
        int cmp = compare(first, o.first);
        return cmp == 0 ? compare(second, o.second) : cmp;
    }

    // todo move this to a helper class.
    private static int compare(Object o1, Object o2) {
        return o1 == null ? o2 == null ? 0 : -1 : o2 == null ? +1
                : ((Comparable) o1).compareTo(o2);
    }

    @Override
    public int hashCode() {
        return 31 * hashcode(first) + hashcode(second);
    }

    // todo move this to a helper class.
    private static int hashcode(Object o) {
        return o == null ? 0 : o.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair))
            return false;
        if (this == obj)
            return true;
        return equal(first, ((Pair) obj).first)
                && equal(second, ((Pair) obj).second);
    }

    // todo move this to a helper class.
    private boolean equal(Object o1, Object o2) {
        return o1 == null ? o2 == null : (o1 == o2 || o1.equals(o2));
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ')';
    }
}
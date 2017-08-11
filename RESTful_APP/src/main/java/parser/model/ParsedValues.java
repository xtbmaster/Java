package parser.model;

import javax.persistence.*;

@Entity
@Table(name = "parsed_file")
public class ParsedValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private int word_id;

    @Column(name = "value")
    private String value;

    @Column(name = "count")
    private int count;

    public ParsedValues(){

    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

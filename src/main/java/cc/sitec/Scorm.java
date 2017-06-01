package cc.sitec;

/**
 * Created by keeley on 2017/6/1.
 */
public class Scorm {
    private String title;
    private String identifier;
    private String isvisible;
    private String identifierref;
    private String type;
    private String href;
    private String scormtype;
    private String parentId;
    private int sequence;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    private Long createTime;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(String isvisible) {
        this.isvisible = isvisible;
    }

    public String getIdentifierref() {
        return identifierref;
    }

    public void setIdentifierref(String identifierref) {
        this.identifierref = identifierref;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getScormtype() {
        return scormtype;
    }

    public void setScormtype(String scormtype) {
        this.scormtype = scormtype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Scorm{" +
                "sequence=" + sequence +
                ", title='" + title + '\'' +
                '}';
    }
}

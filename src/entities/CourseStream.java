
package entities;

import java.util.Objects;


public class CourseStream {
    private int streamid;
    private String streamName;

    public CourseStream() {
    }

    public CourseStream(String streamName) {
        this.streamName = streamName;
    }
    

    public CourseStream(int streamid, String streamName) {
        this.streamid = streamid;
        this.streamName = streamName;
    }

    public int getStreamid() {
        return streamid;
    }

    public void setStreamid(int streamid) {
        this.streamid = streamid;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    @Override
    public String toString() {
        return streamName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.streamid;
        hash = 29 * hash + Objects.hashCode(this.streamName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CourseStream other = (CourseStream) obj;
        if (this.streamid != other.streamid) {
            return false;
        }
        if (!Objects.equals(this.streamName, other.streamName)) {
            return false;
        }
        return true;
    }
    
    
    
}

package edu.tulane.cs.hetml.vision;

public class ImageTriplet {
    private String imageId;
    private int firstSegId;
    private int secondSegId;
    private String trajector;
    private String landmark;
    private String sp;
    private String trBox;
    private String lmBox;
    private String imageBox;
    private String trVector; // F1
    private double trAreawrtLM; // F2
    private double trAspectRatio; // F3
    private double lmAspectRatio; // F3
    private double trAreaBbox; // F4
    private double lmAreaBbox; //F4
    private double iou; // F5
    private double euclideanDistance; // F6
    private double trAreaImage; // F7
    private double lmAreaImage; // F7

    public ImageTriplet(String sp, String trajector, String landmark, String trBox, String lmBox, String imageBox,
                        String trVector, double trAreawrtLM, double trAspectRatio, double lmAspectRatio,
                        double trAreaBbox, double lmAreaBbox, double iou, double euclideanDistance, double trAreaImage,
                        double lmAreaImage) {
        this.setSp(sp);
        this.setTrajector(trajector);
        this.setLandmark(landmark);
        this.trBox = trBox;
        this.lmBox = lmBox;
        this.imageBox = imageBox;
        this.trVector = trVector;
        this.trAreawrtLM = trAreawrtLM;
        this.trAspectRatio = trAspectRatio;
        this.lmAspectRatio = lmAspectRatio;
        this.trAreaBbox = trAreaBbox;
        this.lmAreaBbox = lmAreaBbox;
        this.iou = iou;
        this.euclideanDistance = euclideanDistance;
        this.trAreaImage = trAreaImage;
        this.lmAreaImage = lmAreaImage;
    }

    public ImageTriplet(String imageId, int firstSegId, int secondSegId, String trBox, String lmBox, String imageBox,
                        String trVector, double trAreawrtLM, double trAspectRatio, double lmAspectRatio,
                        double trAreaBbox, double lmAreaBbox, double iou, double euclideanDistance, double trAreaImage,
                        double lmAreaImage) {
        this.setImageId(imageId);
        this.setFirstSegId(firstSegId);
        this.setSecondSegId(secondSegId);
        this.trBox = trBox;
        this.lmBox = lmBox;
        this.imageBox = imageBox;
        this.trVector = trVector;
        this.trAreawrtLM = trAreawrtLM;
        this.trAspectRatio = trAspectRatio;
        this.lmAspectRatio = lmAspectRatio;
        this.trAreaBbox = trAreaBbox;
        this.lmAreaBbox = lmAreaBbox;
        this.iou = iou;
        this.euclideanDistance = euclideanDistance;
        this.trAreaImage = trAreaImage;
        this.lmAreaImage = lmAreaImage;
    }

    public String getSp() {
        return sp;
    }

    public String getImageBox() {
        return imageBox;
    }

    public String getLandmark() {
        return landmark;
    }

    public double getEuclideanDistance() {
        return euclideanDistance;
    }

    public double getIou() {
        return iou;
    }

    public double getLmAreaBbox() {
        return lmAreaBbox;
    }

    public double getLmAreaImage() {
        return lmAreaImage;
    }

    public double getLmAspectRatio() {
        return lmAspectRatio;
    }

    public double getTrAreaBbox() {
        return trAreaBbox;
    }

    public double getTrAreaImage() {
        return trAreaImage;
    }

    public double getTrAreawrtLM() {
        return trAreawrtLM;
    }

    public double getTrAspectRatio() {
        return trAspectRatio;
    }

    public String getLmBox() {
        return lmBox;
    }

    public String getTrajector() {
        return trajector;
    }

    public String getTrBox() {
        return trBox;
    }

    public String getTrVector() {
        return trVector;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public int getFirstSegId() {
        return firstSegId;
    }

    public void setFirstSegId(int firstSegId) {
        this.firstSegId = firstSegId;
    }

    public int getSecondSegId() {
        return secondSegId;
    }

    public void setSecondSegId(int secondSegId) {
        this.secondSegId = secondSegId;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public void setTrajector(String trajector) {
        this.trajector = trajector;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}

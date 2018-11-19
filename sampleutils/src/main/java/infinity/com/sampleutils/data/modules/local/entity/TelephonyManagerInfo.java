package infinity.com.sampleutils.data.modules.local.entity;

public class TelephonyManagerInfo {

    private String countryIso;
    private String mmsUserAgent;
    private String simOperatorName;
    private String simOperator;
    private String dataState;
    private String networkOperator;
    private String networkType;
    private String phoneCount;
    private String simState;
    private String typeConnection;

    public String getTypeConnection() {
        return typeConnection;
    }

    public void setTypeConnection(String typeConnection) {
        this.typeConnection = typeConnection;
    }

    public String getNetworkOperator() {
        return networkOperator;
    }

    public void setNetworkOperator(String networkOperator) {
        this.networkOperator = networkOperator;
    }

    public String getCountryIso() {
        return countryIso;
    }

    public void setCountryIso(String countryIso) {
        this.countryIso = countryIso;
    }

    public String getMmsUserAgent() {
        return mmsUserAgent;
    }

    public void setMmsUserAgent(String mmsUserAgent) {
        this.mmsUserAgent = mmsUserAgent;
    }

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public String getSimOperator() {
        return simOperator;
    }

    public void setSimOperator(String simOperator) {
        this.simOperator = simOperator;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getPhoneCount() {
        return phoneCount;
    }

    public void setPhoneCount(String phoneCount) {
        this.phoneCount = phoneCount;
    }

    public String getSimState() {
        return simState;
    }

    public void setSimState(String simState) {
        this.simState = simState;
    }
}

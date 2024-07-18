package utils.HTMLTable;

public class Action {
    private String actionType;
    private String id;
    private String method;
    private String buttonName;
    private String buttonType;
    private String modalHeader;
    private String modalBody;
    private String url;
    private boolean needSelect;

    public Action(String actionType, String id, String method, String buttonName, String buttonType, String modalHeader, String modalBody, String url, boolean needSelect) {
        this.actionType = actionType;
        this.id = id;
        this.method = method;
        this.buttonName = buttonName;
        this.buttonType = buttonType;
        this.modalHeader = modalHeader;
        this.modalBody = modalBody;
        this.url = url;
        this.needSelect = needSelect;
    }

    public String getActionType() {
        return actionType;
    }

    public String getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public String getButtonName() {
        return buttonName;
    }

    public String getButtonType() {
        return buttonType;
    }

    public String getModalHeader() {
        return modalHeader;
    }

    public String getModalBody() {
        return modalBody;
    }

    public String getUrl() {
        return url;
    }

    public boolean isNeedSelect() {
        return needSelect;
    }
}

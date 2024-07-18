package utils.HTMLTable;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<String> fieldNames = new ArrayList<>();
    private List<String> fieldVariables = new ArrayList<>();
    private List<String> fieldTypes = new ArrayList<>();
    private List<List<String>> fieldOptions = new ArrayList<>();
    private List<Boolean> fieldDisabledAdd = new ArrayList<>();
    private List<Boolean> fieldDisabledUpdate = new ArrayList<>();private List<IRow> data = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();

    public Table() {}

    public List<Boolean> getFieldDisabledAdd() {
        return fieldDisabledAdd;
    }

    public void setFieldDisabledAdd(List<Boolean> fieldDisabledAdd) {
        this.fieldDisabledAdd = fieldDisabledAdd;
    }

    public List<Boolean> getFieldDisabledUpdate() {
        return fieldDisabledUpdate;
    }

    public void setFieldDisabledUpdate(List<Boolean> fieldDisabledUpdate) {
        this.fieldDisabledUpdate = fieldDisabledUpdate;
    }

    public void addRow(IRow row) {
        data.add(row);
    }

    public List<List<String>> getFieldOptions() {
        return fieldOptions;
    }

    public void setFieldOptions(List<List<String>> fieldOptions) {
        this.fieldOptions = fieldOptions;
    }

    public List<String> getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(List<String> fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    public void addRows(List<? extends IRow> rows) {
        data.addAll(rows);
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public List<String> getFieldVariables() {
        return fieldVariables;
    }

    public void setFieldVariables(List<String> fieldVariables) {
        this.fieldVariables = fieldVariables;
    }

    public void setData(List<IRow> data) {
        this.data = data;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<IRow> getData() {
        return data;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public void setFieldProperties(IField obj) {
        this.fieldVariables = obj.getFieldVariables();
        this.fieldNames = obj.getFieldNames();
        this.fieldOptions = obj.getFieldOptions();
        this.fieldTypes = obj.getFieldTypes();
        this.fieldDisabledAdd = obj.getFieldDisabledAdd();
        this.fieldDisabledUpdate = obj.getFieldDisabledUpdate();
    }
}

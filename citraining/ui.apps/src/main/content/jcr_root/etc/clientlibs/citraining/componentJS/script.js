/**
################################################################################
# DESCRIPTION:   
#                            
# Author: Praveen(HCL)
# Environment: CQ 5.6.1
# 
# UPDATE HISTORY
# Version  Developer                     Date        Comments
# 1.0      Praveen (HCL)                                   16-07-2015  Created File
################################################################################  
*/

try {
    if (typeof HCL == 'undefined') {
        HCL = {}; // creating namespace
    }
    HCL.ProductFinderOptions = CQ.Ext.extend(CQ.form.CompositeField, {

    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    hiddenReplaceMultiField : null,
    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    optionId : null,    
    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    optionText : null,


    constructor : function(config) {
        config = config || {};
        var defaults = {
            "border" : true,
            "padding" : 10,
            "style" : "padding:10px 0 0 5px;",
            "layout" : "form",
            "labelWidth" : 80
        };
        config = CQ.Util.applyDefaults(config, defaults);
        HCL.ProductFinderOptions.superclass.constructor
                .call(this, config);
    },

    // overriding CQ.Ext.Component#initComponent
    initComponent : function() {
        HCL.ProductFinderOptions.superclass.initComponent.call(this);

        // Hidden field
        this.hiddenReplaceMultiField = new CQ.Ext.form.Hidden({
            name : this.name
        });
        this.add(this.hiddenReplaceMultiField);

        this.optionId = new CQ.Ext.form.TextField({
            fieldLabel : "Option Text",
            width : 300,
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
       });
        this.add(this.optionId);  
        this.optionText = new CQ.Ext.form.Checkbox ({
            fieldLabel : "is True?",
            width : 300,
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
        this.add(this.optionText);

    },
    // overriding CQ.form.CompositeField#setValue
    setValue : function(value) {
        console.log(value);
        var optionIdVal = '';
        var optionTextVal = '';

        if (value) {
            var colValue = value.split('~');
            if (colValue.length > 0) {
                optionIdVal      = colValue[0];
                optionTextVal      = colValue[1];

            }
        }
        this.optionId.setValue(optionIdVal);
        this.optionText.setValue(optionTextVal);
    },    
    // overriding CQ.form.CompositeField#getValue
    getValue : function() {
        return this.getRawValue();
    },
    getRawValue : function() {
        var optionIdVal      = this.optionId.getValue() || "";
        var optionTextVal      = this.optionText.getValue() || "";
        if(optionTextVal=="") optionTextVal=false;

		var value = optionIdVal + "~"  + optionTextVal ;
        this.hiddenReplaceMultiField.setValue(value);
        console.log(value);
        return value;
    },
    updateHidden : function() {
        this.hiddenReplaceMultiField.setValue(this.getValue());
    }
});
    CQ.Ext.reg('pfquestionsoptions', HCL.ProductFinderOptions);
} catch (e) {
    // suppressing error.
    // error occurs for CQ.form.CompositeField in mobile devices.
}

/**
################################################################################
# DESCRIPTION:   
#                             
# Author: Praveen (HCL)
# Environment: CQ 5.6.1
# 
# UPDATE HISTORY
# Version  Developer                     Date        Comments
# 1.0      Praveen(HCL)                                    16-07-2015          Created File
################################################################################  
 */

try {
    if (typeof HCL == 'undefined') {
        HCL = {}; // creating namespace
    }
    HCL.ProductFinderQuestion = CQ.Ext.extend(CQ.form.CompositeField, {

    /**
     * @private
     * @type CQ.Ext.form.TextField
     */
    hiddenField : null,
    /**
     * @private
     * @type CQ.Ext.form.PathField
     */
    questionId : null,
    /**
     * @private
     * @type CQ.Ext.form.PathField
     */
    questionText : null,
    /**
     * @private
     * @type CQ.Ext.form.MultiField
     */
    optionData : null,
    correct : null,
    incorrect : null,
    selectAny : null,
        correctMain : null,
        incorrectMain : null,

    constructor : function(config) {
        config = config || {};
        var defaults = {
            "border" : true,
            "padding" : 10,
            "style" : "padding:10px 0 0 5px;",
            "layout" : "form",
            "labelWidth" : 200
        };
        config = CQ.Util.applyDefaults(config, defaults);
        HCL.ProductFinderQuestion.superclass.constructor
                .call(this, config);
    },

    // overriding CQ.Ext.Component#initComponent
    initComponent : function() {
        HCL.ProductFinderQuestion.superclass.initComponent.call(this);

        // Hidden field
        this.hiddenField = new CQ.Ext.form.Hidden({
            name : this.name
        });
        this.add(this.hiddenField);

        this.questionText = new CQ.Ext.form.TextField({
            fieldLabel : "Question Text",
            allowBlank: false,
            width : 500,
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                },
                dialogclose : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
        this.add(this.questionText);

        this.optionData = new CQ.form.MultiField({
            fieldLabel : "Options ",
            fieldDescription: "Click '+' to add options",
            width : 500,
            fieldConfig: {
                "xtype" : "pfquestionsoptions"
            },
            listeners: {
                change: {
                    scope:this,
                    fn:this.updateHidden
                }
            }
        });
        this.add(this.optionData);

        this.correctMain = new CQ.Ext.form.TextField({
            fieldLabel : "Correct Answer Message",
            allowBlank: false,
            width : 500,
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                },
                dialogclose : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
        this.add(this.correctMain);

        this.correct = new CQ.Ext.form.TextField({
            fieldLabel : "Correct Answer",
            allowBlank: false,
            width : 500,
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                },
                dialogclose : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
        this.add(this.correct);



        this.incorrectMain = new CQ.Ext.form.TextField({
            fieldLabel : "Incorrect Answer Message",
            allowBlank: false,
            width : 500,
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                },
                dialogclose : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
        this.add(this.incorrectMain);

        this.incorrect = new CQ.Ext.form.TextField({
            fieldLabel : "Incorrect Answer",
            allowBlank: false,
            width : 500,
            listeners : {
                change : {
                    scope : this,
                    fn : this.updateHidden
                },
                dialogclose : {
                    scope : this,
                    fn : this.updateHidden
                }
            }
        });
        this.add(this.incorrect);


    },
    // overriding CQ.form.CompositeField#setValue
    setValue : function(value) {
        var readVal = '';
        var storeVal = '';
        var replaceMultiValues = '';

        var cor;
        var inc;

        var cormain;
        var incmain;
        if (value) {
            var colValue = value.split('|');
            if (colValue.length > 0) {

                storeVal            = colValue[0];
                replaceMultiValues  = colValue[1];
				cormain = colValue[2];
                cor = colValue[3];
                incmain = colValue[4];
                inc = colValue[5];
            }
        }

        this.questionText.setValue(storeVal);
        this.optionData.setValue(replaceMultiValues.split(','));
        this.correct.setValue(cor);
        this.incorrect.setValue(inc);
        this.correctMain.setValue(cormain);
        this.incorrectMain.setValue(incmain);
    },    
    // overriding CQ.form.CompositeField#getValue
    getValue : function() {
        return this.getRawValue();
    },
    getRawValue : function() {

        var storeVal            = this.questionText.getValue() || "";
        var replaceMultiValues  = this.optionData.getValue() || "";
        var correctAnswer		= this.correct.getValue() || "";
        var correctAnswerMain		= this.correctMain.getValue() || "";
        var incorrectAnswer  	= this.incorrect.getValue() || "";
        var incorrectAnswerMain  	= this.incorrectMain.getValue() || "";



        if (replaceMultiValues == '')
            replaceMultiValues = " ";

        var value = storeVal + "|" 
                    + replaceMultiValues + "|" 
                    + correctAnswerMain + "|" 
                    + correctAnswer + "|"
                    + incorrectAnswerMain + "|" 
                    + incorrectAnswer ;
        this.hiddenField.setValue(value);
        return value;
    },
    updateHidden : function() {
        this.hiddenField.setValue(this.getValue());
    }
});
    CQ.Ext.reg('pfquestions', HCL.ProductFinderQuestion);
} catch (e) {
    // suppressing error.
}

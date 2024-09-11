package com.nibiru.server.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MySQLCommentGenerator extends EmptyCommentGenerator {

    private Properties properties;

    public MySQLCommentGenerator() {
        properties = new Properties();
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        // 获取自定义的 properties
        this.properties.putAll(properties);
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String author = properties.getProperty("author");
        String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd");
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        // 获取表注释
        String remarks = introspectedTable.getRemarks();
        //System.out.println("表注释========"+remarks);
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @author: " + author);
        topLevelClass.addJavaDocLine(" * @date: " + dateFormatter.format(new Date()));
        topLevelClass.addJavaDocLine(" * @description: "+remarks);
        topLevelClass.addJavaDocLine(" */");


//        final FullyQualifiedJavaType serializable = new FullyQualifiedJavaType("java.io.Serializable");
//        topLevelClass.addImportedType(serializable);   //import java.io.Serializable;
//        topLevelClass.addSuperInterface(serializable); //实现java.io.Serializable接口

        //添加serialVersionUID字段
        //最终生成代码private static final long serialVersionUID = 1L;
//        Field field = new Field();
//        field.setFinal(true);    //添加final修饰
//        field.setInitializationString("1L"); //$NON-NLS-1$  赋值为1L
//        field.setName("serialVersionUID"); //$NON-NLS-1$    设置字段名称为serialVersionUID
//        field.setStatic(true);  //添加static关键字
//        field.setType(new FullyQualifiedJavaType("long")); //$NON-NLS-1$  声明类型
//        field.setVisibility(JavaVisibility.PRIVATE);//声明为私有

//        topLevelClass.addField(field);

    }

    /**
     *
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 获取列注释
        String remarks = introspectedColumn.getRemarks();
        field.addJavaDocLine("/** "+remarks+" */");
        //field.addJavaDocLine(" * " + remarks);
        //field.addJavaDocLine(" */");
    }

    @Override
    public void addRootComment(XmlElement xmlElement) {
    }
}

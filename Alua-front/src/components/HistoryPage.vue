<template>
  <div>
    <h1>HistroyPage</h1>
    <el-table :data="chatInfoData" style="line-height: normal" height="750">
      <el-table-column label="对话ID" width="180" height="50">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.cid }}</span>
        </template>
      </el-table-column>

      <el-table-column label="角色" width="180" height="50">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.role }}</span>
        </template>
      </el-table-column>

      <el-table-column label="日期" width="180" height="50" sortable>
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.mdate }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="对话内容"
        width="350"
        height="50"
        inline
        fit="false"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{
            scope.row.content.length > 20
              ? scope.row.content.substring(0, 20) + "..."
              : scope.row.content
          }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" height="50">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
          <el-button
            size="mini"
            type="primary"
            @click="handleShow(scope.$index, scope.row)"
            >查看对话</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      layout="sizes, prev, pager, next"
      :total="total"
    >
    </el-pagination>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from "vue";
</script>

<style scoped lang="less"></style>

<template>
    <div>
  <h3>div默认是垂直方向排列的</h3>
    <div style="background-color: #d0d0d0;height: 250px;">
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>

    <h3>盒子模型默认是水平方向排列</h3>
    <div style="background-color: #d0d0d0;height: 250px;display: flex;">
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型默认是水平方向排列flex-direction: row</h3>
    <div style="background-color: #d0d0d0;height: 250px;display: flex; flex-direction: row;">
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>

    <h3>盒子模型从右到左排列flex-direction: row-reverse</h3>
    <div
      style="background-color: #d0d0d0;height: 250px;display: flex; flex-direction: row-reverse;"
    >
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型默认是垂直方向排列</h3>
    <div style="background-color: #d0d0d0;height: 250px;display: flex; flex-direction: column;">
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型默认是垂直方向排列反转</h3>
    <div
      style="background-color: #d0d0d0;height: 250px;display: flex; flex-direction: column-reverse;"
    >
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>

    <h3>盒子模型右对齐方式</h3>
    <div style="background-color: #d0d0d0;height: 250px;display: flex; justify-content: flex-end;">
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型中间对齐方式</h3>
    <div style="background-color: #d0d0d0;height: 250px;display: flex; justify-content: center;">
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型左对齐方式</h3>
    <div
      style="background-color: #d0d0d0;height: 250px;display: flex; justify-content: flex-start;"
    >
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型两边对齐方式</h3>
    <div
      style="background-color: #d0d0d0;height: 250px;display: flex; justify-content: space-between;"
    >
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型两边around对齐方式</h3>
    <div
      style="background-color: #d0d0d0;height: 250px;display: flex; justify-content: space-around;"
    >
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型交叉轴起点对齐方式</h3>
    <div style="background-color: #d0d0d0;height: 250px;display: flex; align-items: flex-start;">
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型交叉轴居中对齐方式</h3>
    <div style="background-color: #d0d0d0;height: 250px;display: flex; align-items: center;">
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型交叉轴尾部对齐方式</h3>
    <div style="background-color: #d0d0d0;height: 250px;display: flex; align-items:flex-end;">
      <div style="background-color: #D9006C;height: 50px;width: 50px;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px;">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px;">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px;">4</div>
    </div>
    <h3>盒子模型平分</h3>
    <div style="background-color: #d0d0d0;height: 250px;display: flex;">
      <div style="background-color: #D9006C;height: 50px;width: 50px; flex-grow: 1;">1</div>
      <div style="background-color: #FF8F59;height: 50px;width: 50px; flex-grow: 1">2</div>
      <div style="background-color: rgb(82, 76, 76);height: 50px;width: 50px; flex-grow: 1">3</div>
      <div style="background-color: #AD5A5A;height: 50px;width: 50px; flex-grow: 1">4</div>
    </div>

    </div>
</template>

<script>
    export default {
        
    }
</script>

<style lang="scss" scoped>

</style>
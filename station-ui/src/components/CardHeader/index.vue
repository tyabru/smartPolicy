
<template>
  <div :class="['card-header', className]" style="{'margin-bottom': marginBottom}">
    <div :class="['card-header__text', textClassName]">
      <i v-if="icon" :class="icon"></i>
      <span>{{ headerText }}</span>
    </div>
    <div class="suffix">
      <slot></slot>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'CardHeader',
    props: {
      icon: {
        type: String
      },
      headerText: {
        type: String
      },
      className: {
        type: String
      },
      textClassName: {
        type: String
      },
      marginBottom: {
        type: String,
        default() {
          return '10px'
        }
      }
    }
  }
</script>

<style scoped lang="scss">
  .card-header {
    position: relative;
    .card-header__text {
      font-size: 16px;
      font-weight: 700;
      i {
        margin-right: 8px;
      }
    }
    .suffix {
      position: absolute;
      top: 0;
      right: 0;
    }
  }
</style>

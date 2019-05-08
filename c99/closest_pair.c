#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <float.h>

#define SQ(x) ((x) * (x))
#define MIN(a, b) ((a) < (b) ? (a) : (b))

typedef struct point_t {
    double x, y;
} point_t;

typedef struct pair_t {
    point_t a, b;
    double d;
} pair_t;

typedef struct helper_t {
    pair_t pair;
    point_t* points;
    size_t len;
} helper_t;

int cmp_x(const void *p, const void *q) {
    const point_t a = *((point_t*) p), b = *((point_t*) q);
    return a.x >= b.x ? 1 : -1;
}

int cmp_y(const void *p, const void *q) {
    const point_t a = *((point_t*) p), b = *((point_t*) q);
    return a.y >= b.y ? 1 : -1;
}

double distance_sq(point_t a, point_t b) {
    return SQ(a.x - b.x) + SQ(a.y - b.y);
}

pair_t brute_force(point_t* points, size_t n) {
    double min = FLT_MAX;
    point_t min1, min2;

    for(size_t i = 0; i < n; i++) {
        const point_t p1 = points[i];
        for(size_t j = i + 1; j < n; j++) {
            const point_t p2 = points[j];
            double dist = distance_sq(p1, p2);
            if(dist < min) {
                min = dist;
                min1 = p1;
                min2 = p2;
            }
        }
    }
    return (pair_t) {min1, min2, min};
}

point_t* merge_sort(point_t* a, size_t la, point_t* b, size_t lb) {
    size_t i = 0, j = 0;
    point_t* result = malloc((la + lb) * sizeof(point_t));
    size_t len = 0;
    while(i < la && j < lb) {
        const point_t left = a[i], right = b[j];
        if(left.y < right.y) {
            result[len++] = left;
            i++;
        } else {
            result[len++] = right;
            j++;
        }
    }
    for(size_t k = i; k < la; k++)
        result[len++] = a[k];
    for(size_t k = j; k < lb; k++)
        result[len++] = b[k];
    return result;
}

helper_t find_closest(point_t* points, size_t n) {
    if(n <= 3) {
        qsort((void*) points, n, sizeof(point_t), cmp_y);
        return (helper_t) {brute_force(points, n), points, n};
    }

    const size_t mid = n / 2;
    const point_t p_mid = points[mid];
    const helper_t d1 = find_closest(points, mid), d2 = find_closest(points + mid, n - mid);

    pair_t d = d1.pair.d <= d2.pair.d ? d1.pair : d2.pair;

    point_t* merged = merge_sort(d1.points, d1.len, d2.points, d2.len);
    const size_t merged_len = d1.len + d2.len;

    point_t* between = malloc(merged_len * sizeof(point_t));
    size_t between_len = 0;
    for(int i = 0; i < merged_len; i++) {
        const point_t p = merged[i];
        if(fabs(p.x - p_mid.x) < d.d)
            between[between_len++] = p;
    }

    for(size_t i = 0; i < between_len; i++) {
        const point_t p1 = between[i];
        const size_t min = MIN(between_len, i + 6);
        for(size_t j = i + 1; j < min; j++) {
            const point_t p2 = between[j];
            if(p1.y - p2.y >= d.d)
                break;
            double b_dist = distance_sq(p1, p2);
            if(b_dist < d.d) {
                d.d = b_dist;
                d.a = p1;
                d.b = p2;
            }
        }
    }

    free(between);

    return (helper_t) {d, merged, merged_len};
}

pair_t solve(point_t* points, size_t n) {
    qsort((void*) points, n, sizeof(point_t), cmp_x);
    return find_closest(points, n).pair;
}

int main(void) {
    point_t* points = NULL;
    while(1) {
        const int c = getchar();
        if(c == '0')
            break;
        ungetc(c, stdin);

        int n;
        scanf("%d\n", &n);
        points = realloc(points, n * sizeof(point_t));

        for(int i = 0; i < n; i++) {
            point_t* p = points + i;
            scanf("%lf %lf\n", &(p->x), &(p->y));
        }

        pair_t closest = solve(points, (size_t) n);

        printf("%lf %lf %lf %lf\n", closest.a.x, closest.a.y, closest.b.x, closest.b.y);
    }

    return 0;
}
